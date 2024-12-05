package org.example.webctf1.service;

import org.example.webctf1.dao.User;

public interface UserService {

    public User findById(int uid);

    // 根据用户名查询用户
    User findByUserName(String username);
    // 注册
    void register(String username, String password);

    void updatePwd(String newPwd);
}
