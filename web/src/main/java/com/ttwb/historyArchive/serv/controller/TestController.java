package com.ttwb.historyArchive.serv.controller;

import com.ttwb.historyArchive.serv.model.User;
import com.ttwb.historyArchive.serv.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2019/2/1 16:17
 * @Created by zhoulq
 */
//@Controller
public class TestController
{
    @Autowired
    TestService testService;



    public List<User> testUser()
    {
        System.out.println("测试mybatis");
        List<User> list=testService.getUser();
        return list;
    }




}
