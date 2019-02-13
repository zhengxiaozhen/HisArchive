package com.ttwb.historyArchive.web.controller;

import com.ttwb.historyArchive.serv.model.User;
import com.ttwb.historyArchive.serv.model.pv.UserPageView;
import com.ttwb.historyArchive.serv.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * @Classname indexReource
 * @Description TODO
 * @Date 2019/2/1 0:28
 * @Created by zhoulq
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
@RequestMapping("/login")
public class LoginUser2Resource
{

    @Autowired
    LoginUserService userService;

    /**
     * @param request
     * @param userPageView
     * @return 用户登录资源请求   保存session   /login/user
     */
    @POST
    @RequestMapping("/user")
    @ResponseBody
    public Object getStudent(HttpServletRequest request, @RequestBody UserPageView userPageView)
    {


        HttpSession session = request.getSession(true);
        String flag = "{}";

        if (userPageView.getUserName() == null || userPageView.getPassword() == null)
        {
            flag = "{\"data\":\"账号或密码不为空\"}";
        } else
        {
            User user = userService.getUserInfo(userPageView.getUserName(), userPageView.getPassword());


            if (user != null)
            {
                session.setAttribute("userName", user.getUserName());
                session.setAttribute("npoliceId", user.getNpoliceId());
                session.setAttribute("userId", user.getUserId());
                flag = "{\"data\":\"true\"}";
            } else
            {
                flag = "{\"data\":\"账号或密码不正确\"}";
            }

        }

        //测试分页查询
        // PageInfo<User> page=(PageInfo<User>) userService.getUserPageList(userPageView.getUserName(),0,10);

        //测试新增
        /*userPageView.setUserName("admintest");
        userPageView.setPassword("test");
        userPageView.setNpoliceId("1111");
        boolean ff=(Boolean) userService.saveUserInfo(userPageView);
        if (ff)
        {
            flag = "{\"data\":\"true\"}";
        }else {
            flag = "{\"data\":\"保存异常\"}";
        }*/
        //测试更新
        /*userPageView.setUserName("adminbbbb");
        userPageView.setPassword("testttt");
        userPageView.setNpoliceId("1111222");
        userPageView.setUserId("6");
        userPageView.setZxbz("1");
        boolean ff=(Boolean) userService.updateUserInfo(userPageView);
        if (ff)
        {
            flag = "{\"data\":\"true\"}";
        }else {
            flag = "{\"data\":\"更新异常\"}";
        }
        */
        //测试删除

        /*userPageView.setUserId("6");
        boolean ff = (Boolean) userService.deleteUserInfo(userPageView.getUserId());
        if (ff)
        {
            flag = "{\"data\":\"true\"}";
        } else
        {
            flag = "{\"data\":\"更新异常\"}";
        }*/
        return flag;

    }
}

