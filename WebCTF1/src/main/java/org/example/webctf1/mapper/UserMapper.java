package org.example.webctf1.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.webctf1.dao.User;

@Mapper
public interface UserMapper {

    @Select("select * from user where uid = #{uid}")
    public User findById(int uid);

    @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    @Insert("insert into user(username,password,type) values (#{username},#{password},'0')") // 默认type都是0，注册都不是管理员
    void add(String username, String password);

    @Update("update user set password=#{newPwd} where uid = #{uid}")
    void updatePwd(String newPwd, int uid);
}
