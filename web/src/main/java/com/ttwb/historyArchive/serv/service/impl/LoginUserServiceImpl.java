package com.ttwb.historyArchive.serv.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttwb.historyArchive.serv.mapper.LoginUserMapper;
import com.ttwb.historyArchive.serv.model.User;
import com.ttwb.historyArchive.serv.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by user on 2017/1/21.
 */
//@Service   bean.xml配置通用的bean实例化
public class LoginUserServiceImpl implements LoginUserService
{


    @Autowired
    private LoginUserMapper loginUserMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;


    /* @Override
     public List<User> getUser()
     {
         return testMapper.getUser();
     }
 */
    @Override
    public Object getUserPageList(String userName, int offset, int limit)
    {

        PageHelper.offsetPage(offset, limit);
        List<User> list = loginUserMapper.selectUserList(userName);
        return new PageInfo<>(list);
    }


    @Override
    public User getUserInfo(String userName, String password)
    {


        return loginUserMapper.selectUserByName(userName, password);
    }

    public Object saveUserInfo(User user)
    {

        return transactionTemplate.execute(new TransactionCallback()
        {
            //Object :表示执行doInTransaction方法返回的结果
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus)
            {
                boolean flag = false;
                try
                {
                    loginUserMapper.insertUserInfo(user);
                    flag = true;
                } catch (Exception e)
                {
                    e.printStackTrace();
                    transactionStatus.isRollbackOnly();
                    flag = false;
                } finally
                {
                    return flag;
                }

            }
        });


    }

    public Object updateUserInfo(User user)
    {

        return transactionTemplate.execute(new TransactionCallback()
        {
            //Object :表示执行doInTransaction方法返回的结果
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus)
            {
                boolean flag = false;
                try
                {
                    loginUserMapper.updateUserInfo(user);
                    flag = true;
                } catch (Exception e)
                {
                    e.printStackTrace();
                    transactionStatus.isRollbackOnly();
                    flag = false;
                } finally
                {
                    return flag;
                }

            }
        });


    }

    @Override
    public Object deleteUserInfo(String userId)
    {
        if (StringUtils.isEmpty(userId))
        {
            return false;
        }
        return transactionTemplate.execute(new TransactionCallback()
        {
            //Object :表示执行doInTransaction方法返回的结果
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus)
            {
                boolean flag = false;
                try
                {
                    loginUserMapper.deleteUserInfo(userId);
                    flag = true;
                } catch (Exception e)
                {
                    e.printStackTrace();
                    transactionStatus.isRollbackOnly();
                    flag = false;
                } finally
                {
                    return flag;
                }

            }
        });


    }

}
