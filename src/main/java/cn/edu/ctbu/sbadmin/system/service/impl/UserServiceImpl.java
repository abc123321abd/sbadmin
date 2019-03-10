package cn.edu.ctbu.sbadmin.system.service.impl;


import cn.edu.ctbu.sbadmin.common.core.AbstractService;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.dao.UserDao;
import cn.edu.ctbu.sbadmin.system.domain.UserDO;
import cn.edu.ctbu.sbadmin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class UserServiceImpl  extends AbstractService<UserDO> implements UserService {
    @Autowired
    private UserDao userDao;




    public UserDO get(Long id) {
        UserDO user = super.get(id);
        user.setPassword("");

        return user;
    }

    @Override
    public List<UserDO> getByUserName(String userName)
    {
        List<UserDO> userList = super.findByAttrName("username",userName);

        return userList;
    }

    @Override
    public String getUserPassword(Long id) {
        UserDO user = super.get(id);

        if (user != null)
        {
            return user.getPassword();
        } else {
            return "";
        }
    }

    @Override
    public List<UserDO> list(MQuery mquery) {
        List<UserDO> userList = super.list(mquery);

        //去掉所有的密码，为了安全
        for (UserDO item :  userList) {
            item.setPassword("");
        }

        return userList;
    }


}
