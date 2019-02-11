package com.ttwb.historyArchive.mapper;


import com.ttwb.historyArchive.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestMapper
{

    List<User> getUser();

    List<User> selectUserList(@Param("age")Integer age);

    User selectUserByName(@Param("name") String name);

    int deleteByName(@Param("name") String name);
}