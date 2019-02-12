package com.ttwb.historyArchive.serv.service.login;


import com.ttwb.historyArchive.serv.model.User;

/**
 * Created by niezonghui on 2017/6/2.
 */
public interface LoginUserService
{
    /*List<User> getUser();
    Object getUserPageList(Integer age, int offset, int limit);*/
    User getUserInfo(String userName,String password);

   // void deleteUserInfo(String name);

}
