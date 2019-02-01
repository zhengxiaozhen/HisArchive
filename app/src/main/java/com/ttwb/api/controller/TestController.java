package com.ttwb.api.controller;

import com.ttwb.api.model.User;
import com.ttwb.api.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2019/2/1 16:17
 * @Created by zhoulq
 */
@Controller
public class TestController
{
    @Autowired
    TestServiceImpl testService;



    public List<User> testUser()
    {
        System.out.println("测试mybatis");
        List<User> list=testService.getUser();
        return list;
    }




}
