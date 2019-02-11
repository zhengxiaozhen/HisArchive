package com.ttwb.historyArchive.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttwb.historyArchive.model.User;
import com.ttwb.historyArchive.service.TestService;
import com.ttwb.historyArchive.mapper.TestMapper;
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

        transactionTemplate.execute(new TransactionCallback()
        {
            //Object :表示执行doInTransaction方法返回的结果
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus)
            {
                testMapper.deleteByName(name);
                return null;
            }
        });

    }
}
