package org.example.webctf1.controller;

import com.zaxxer.hikari.HikariDataSource;
import org.example.webctf1.dao.Result;
import org.example.webctf1.dao.User;
import org.example.webctf1.service.UserService;
import org.example.webctf1.utils.JwtUtil;
import org.example.webctf1.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private HikariDataSource dataSource;

    // 登陆
    @PostMapping("/login")
    public Result<String> login(String username, String password) {
        // 根据用户名查用户
        System.out.println(username);
        System.out.println(password);
        User loginUser = userService.findByUserName(username);
        // 判断用户是否存在
        if (loginUser==null) {
            return Result.error("用户名错误");
        }
        // 判断密码是否正确
        if(password.equals(loginUser.getPassword())) {
            // 生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("uid",loginUser.getUid());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);

            // 先清除redis里面的当前用户的jwt，防止多处同时登录
            Set<String> keys = stringRedisTemplate.keys("*"); // 使用KEYS *命令查找所有键
            for (String key : keys) {
                // 解密token
                Map<String, Object> parsedClaims = JwtUtil.parseToken(key);
                if (parsedClaims.containsKey("uid") && parsedClaims.get("uid").equals(loginUser.getUid())) {
                    stringRedisTemplate.delete(key); // 删除与同一ID相关的token
                }
            }

            // 将token存入redis
            stringRedisTemplate.opsForValue().set(token, token, 1, TimeUnit.HOURS);

            System.out.println(token);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    // 注册
    @PostMapping("/register")
    public Result register(String username, String password) {
        System.out.println(username);
        System.out.println(password);

        // 判断注册的用户名和密码的长度是否合法 username(3-16) password(5-16)
        if (username !=null && username.length()>=3 && username.length()<=16 &&
                password!=null && password.length()>=6 && password.length()<=16) {
            User u = userService.findByUserName(username);
            if (u == null) {
                userService.register(username,password);
                return Result.success();
            }else {
                return Result.error("用户名已被占用");
            }
        }else
            return Result.error("参数不合法");
    }

    // 用户信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/) {

        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");

        // Map<String, Object> map = JwtUtil.parseToken(token);
        // String username = (String) map.get("username");

        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    // 修改密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        // 1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");


        if ( !StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd) ) {
            return Result.error("缺少必要的参数");
        }
        //原始密码是否正确
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
//        System.out.println(loginUser.getPassword());
//        System.out.println(oldPwd);
        if (!loginUser.getPassword().equals(oldPwd)) {
            return Result.error("原密码不正确");
        }

        if (!newPwd.equals(rePwd)) {
            return Result.error("两次填写的密码不同");
        }
        // 2.调用service完成密码更新
        userService.updatePwd(newPwd);
        // 删除redis里面的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }

}
