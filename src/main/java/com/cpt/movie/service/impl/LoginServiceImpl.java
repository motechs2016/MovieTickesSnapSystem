package com.cpt.movie.service.impl;

import com.cpt.movie.dao.LoginDao;
import com.cpt.movie.log.AutoLogger;
import com.cpt.movie.pojo.Login;
import com.cpt.movie.service.LoginService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by cpt72 on 2016/12/12.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @AutoLogger
    private Logger logger;

    @Autowired
    @Qualifier("loginDaoImpl")
    private LoginDao loginDao;

    @Override
    public int loginByEmail(String email) {
        logger.debug("enter into LoginServiceImpl-loginByEmail email:" + email);
        Login login = loginDao.selectByEmail(email);
        int id = 0;
        if (login == null) {
            //不存在创建新用户
            login = new Login();
            login.setEmail(email);
            login.setRegisterTime(new Date());
            id = loginDao.insert(login);
        } else {
            id = login.getId();
        }
        return id;
    }
}
