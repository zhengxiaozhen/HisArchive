package com.ttwb.historyArchive.serv.mapper;


import com.ttwb.historyArchive.serv.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginUserMapper
{

    // List<User> getUser();

    List<User> selectUserList(@Param("userName") String userName);

    User selectUserByName(@Param("userName") String userName, @Param("password") String password);

    void insertUserInfo(User user);

    void updateUserInfo(User user);

    int deleteUserInfo(@Param("userId") String userId);
}