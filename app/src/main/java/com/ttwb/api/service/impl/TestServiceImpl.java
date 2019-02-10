package com.ttwb.api.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttwb.api.model.User;
import com.ttwb.api.service.TestService;
import com.ttwb.dal.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by user on 2017/1/21.
 */
@Service
public class TestServiceImpl implements TestService
{


    @Autowired
    private TestMapper testMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;



    @Override
    public List<User> getUser()
    {
        return testMapper.getUser();
    }

    @Override
    public Object getUserPageList(Integer age, int offset, int limit) {

        PageHelper.offsetPage(offset, limit);
        List<User> list = testMapper.selectUserList(age);
        return new PageInfo<>(list);
    }


    @Override
    public User getUserInfo(String name)
    {


        return testMapper.selectUserByName(name);
    }

    @Override
    public void deleteUserInfo(String name)
    {
        if (StringUtils.isEmpty(name))
        {
            return;
        }

        transactionTemplate.execute(new TransactionCallback<Void>()
        {
            @Override
            public Void doInTransaction(TransactionStatus transactionStatus)
            {

                testMapper.deleteByName(name);
                return null;
            }
        });

    }
}
