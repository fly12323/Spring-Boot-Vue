package org.example.webctf1.service.impl;

import org.example.webctf1.dao.User;
import org.example.webctf1.mapper.UserMapper;
import org.example.webctf1.service.UserService;
import org.example.webctf1.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 通过uid找一条数据
    @Override
    public User findById(int uid){
        return userMapper.findById(uid);
    }

    // 通过username找
    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    // 注册，添加账户
    @Override
    public void register(String username, String password) {
        userMapper.add(username,password);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        int uid = (int) map.get("uid");
        userMapper.updatePwd(newPwd,uid);
    }


}
