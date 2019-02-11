package com.ttwb.historyArchive.serv.mapper;


import com.ttwb.historyArchive.serv.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestMapper
{

    List<User> getUser();

    List<User> selectUserList(@Param("age")Integer age);

    User selectUserByName(@Param("name") String name);

    int deleteByName(@Param("name") String name);
}