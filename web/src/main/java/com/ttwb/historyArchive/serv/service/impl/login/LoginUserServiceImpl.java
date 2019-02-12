package com.ttwb.historyArchive.serv.service.impl.login;


import com.ttwb.historyArchive.serv.mapper.LoginUserMapper;
import com.ttwb.historyArchive.serv.model.User;
import com.ttwb.historyArchive.serv.service.login.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

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

    @Override
    public Object getUserPageList(Integer age, int offset, int limit) {

        PageHelper.offsetPage(offset, limit);
        List<User> list = testMapper.selectUserList(age);
        return new PageInfo<>(list);
    }

*/
    @Override
    public User getUserInfo(String userName,String password)
    {


        return loginUserMapper.selectUserByName(userName,password);
    }

   /* @Override
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

    }*/
}
