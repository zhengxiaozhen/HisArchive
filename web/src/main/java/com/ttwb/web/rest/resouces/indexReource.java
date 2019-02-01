package com.ttwb.web.rest.resouces;

import com.ttwb.api.controller.TestController;
import com.ttwb.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @Classname indexReource
 * @Description TODO
 * @Date 2019/2/1 0:28
 * @Created by zhoulq
 */
@Controller
@RequestMapping("/mvc")
public class indexReource
{
    @Autowired
    TestController testController;
    @RequestMapping("/hello")
    public String getStudent()
    {
        System.out.println("调用到视图");

        List<User> list=testController.testUser();
        for (User user : list)
        {
            System.out.println("name:"+user.getUserName());
            System.out.println("age:"+user.getAge());

        }
        return "hello";
    }
}

