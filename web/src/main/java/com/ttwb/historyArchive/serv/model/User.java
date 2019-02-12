package com.ttwb.historyArchive.serv.model;

import java.io.Serializable;

/**
 * @Classname User
 * @Description TODO
 * @Date 2019/2/1 15:52
 * @Created by zhoulq
 */
public class User implements Serializable
{


    private static final long serialVersionUID = 2413915666727062547L;
    private String userId;
    private String userName;
    private String password;
    private String npoliceId;
    private String zxbz;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getNpoliceId()
    {
        return npoliceId;
    }

    public void setNpoliceId(String npoliceId)
    {
        this.npoliceId = npoliceId;
    }

    public String getZxbz()
    {
        return zxbz;
    }

    public void setZxbz(String zxbz)
    {
        this.zxbz = zxbz;
    }
}
