package com.ttwb.dal.mapper;


import com.ttwb.api.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestMapper
{

    List<User> getUser();

    List<User> selectUserList(@Param("age")Integer age);

}