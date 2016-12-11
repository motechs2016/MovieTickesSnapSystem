package com.cpt.movie.dao.impl;

import com.cpt.movie.dao.LoginDao;
import com.cpt.movie.dao.datanucleus.dao.DataNucleusDao;
import com.cpt.movie.pojo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cpt72 on 2016/12/11.
 */
@Repository
public class LoginDaoImpl implements LoginDao {
    @Autowired
    @Qualifier("dataNucleusDaoImpl")
    private DataNucleusDao dataNucleusDao;
    @Override
    public int insert(Login login) {
        return (int) dataNucleusDao.insert(login);
    }

    @Override
    public boolean update(Login login) {
        return dataNucleusDao.update(login);
    }

    @Override
    public Login selectById(int id) {
        return dataNucleusDao.selectByPrimaryKey(Login.class,id);
    }

    @Override
    public Login selectByUsername(String username) {
        String query="username=='"+username.trim()+"'";
        List<Login> logins = dataNucleusDao.selectByQuery(Login.class, query);
        if (logins!=null&&!logins.isEmpty())
            return logins.get(0);
        return null;
    }

    @Override
    public Login selectByEmail(String email) {
        String query=" email=='"+email.trim()+"'";
        List<Login> logins = dataNucleusDao.selectByQuery(Login.class, query);
        if (logins!=null&&!logins.isEmpty())
            return logins.get(0);
        return null;
    }
}
