package com.ttwb.historyArchive.serv.service;


import com.ttwb.historyArchive.serv.model.User;

/**
 * Created by niezonghui on 2017/6/2.
 */
public interface LoginUserService
{
    //List<User> getUser();
    Object getUserPageList(String userName, int offset, int limit);

    User getUserInfo(String userName, String password);

    Object saveUserInfo(User user);

    Object updateUserInfo(User user);

    Object deleteUserInfo(String userId);


}
