package org.example.webctf1.dao;

import lombok.Data; // 自动生成Getter和Setter
import net.minidev.json.annotate.JsonIgnore;

@Data
public class User {
    // 登录的实体类
    private int uid;
    private String username;
    @JsonIgnore   // 密码转json的时候就不会显示
    private String password;
    private String type;
}
