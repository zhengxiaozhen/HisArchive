package com.ttwb.historyArchive.serv.mapper;


import com.ttwb.historyArchive.serv.model.User;
import org.apache.ibatis.annotations.Param;

public interface LoginUserMapper
{

   // List<User> getUser();

    //List<User> selectUserList(@Param("age") Integer age);

    User selectUserByName(@Param("userName") String userName, @Param("password") String password);

    //int deleteByName(@Param("name") String name);
}