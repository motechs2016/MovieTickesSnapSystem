package lab.io.rush.service.impl;

import lab.io.rush.dao.LoginDao;
import lab.io.rush.log.AutoLogger;
import lab.io.rush.pojo.Login;
import lab.io.rush.service.LoginService;
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
