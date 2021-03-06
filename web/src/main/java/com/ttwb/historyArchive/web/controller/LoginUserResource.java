package com.ttwb.historyArchive.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ttwb.historyArchive.serv.model.User;
import com.ttwb.historyArchive.serv.model.pv.UserPageView;
import com.ttwb.historyArchive.serv.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * @Classname indexReource
 * @Description TODO
 * @Date 2019/2/1 0:28
 * @Created by zhoulq
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("/login")
public  class LoginUserResource
{

    @Autowired
    LoginUserService userService;

    /**
     *
     * @param request
     * @param userPageView
     * @return
     * 用户登录资源请求   保存session   /login/user
     */
    @POST
    @Path("/user1")
    public Response getStudent(@Context HttpServletRequest request ,UserPageView userPageView )
    {
        //System.out.println("Path调用到视图");
        /*UserPageView userPageView=new UserPageView();
        userPageView.setUserName("admin");
        userPageView.setPassword("admin");*/

        HttpSession session=   request.getSession(true);
        User user=userService.getUserInfo(userPageView.getUserName(),userPageView.getPassword());
        String userF="{}";



        if (user!=null)
        {
            session.setAttribute("userName",user.getUserName());
            session.setAttribute("npoliceId",user.getNpoliceId());
            session.setAttribute("userId",user.getUserId());
            userF="{\"data\":\"true\"}";
        }else {
            userF="{\"data\":\"账号或密码不正确\"}";
        }

        JSONObject jsonObject= JSON.parseObject(userF);
        //没有查找到数据返回false
        return Response.ok(jsonObject).build();

    }
}

