package com.ttwb.web.rest.resouces;

import com.github.pagehelper.PageInfo;
import com.ttwb.api.controller.TestController;
import com.ttwb.api.model.User;
import com.ttwb.api.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * @Classname indexReource
 * @Description TODO
 * @Date 2019/2/1 0:28
 * @Created by zhoulq
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("/path")
public  class pathReource
{
    @Autowired
    TestController testController;
    @Autowired
    TestServiceImpl testService;
    @GET
    @Path("/hello")
    public Response getStudent()
    {
        System.out.println("Path调用到视图");

        List<User> list=testController.testUser();
        for (User user : list)
        {
            System.out.println("name:"+user.getUserName());
            System.out.println("age:"+user.getAge());
            //System.out.println(Response.ok("name"+user.getUserName()).build());
        }

        PageInfo<User> page=(PageInfo<User>) testService.getUserPageList(14,0,10);
        return Response.ok(page).build();
    }
}

