package com.ttwb.api.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttwb.api.model.User;
import com.ttwb.api.service.TestService;
import com.ttwb.dal.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

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

   /* @Override
    public int insertAudit(AuditEnterpriseInfo auditEnterpriseInfo)
    {

        int auditEnterpriseId = auditMapper.insertAudit(auditEnterpriseInfo);
        return auditEnterpriseId;

    }*/

    @Override
    public List<User> getUser()
    {
        List<User> user = testMapper.getUser();
        return user;
    }

    @Override
    public Object getUserPageList(Integer age, int offset, int limit) {

        PageHelper.offsetPage(offset, limit);
        List<User> list = testMapper.selectUserList(age);
        return new PageInfo<>(list);
    }


}
