package com.ttwb.historyArchive.serv.service;


import com.ttwb.historyArchive.serv.model.User;

import java.util.List;

/**
 * Created by niezonghui on 2017/6/2.
 */
public interface TestService
{
    List<User> getUser();
    Object getUserPageList(Integer age, int offset, int limit);
    User getUserInfo(String name);

    void deleteUserInfo(String name);

}
