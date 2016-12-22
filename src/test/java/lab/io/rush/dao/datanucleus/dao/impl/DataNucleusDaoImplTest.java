package lab.io.rush.dao.datanucleus.dao.impl;

import lab.io.rush.dao.datanucleus.dao.DataNucleusDao;
import lab.io.rush.pojo.Login;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by cpt72 on 2016/12/11.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataNucleusDaoImplTest {
    @Autowired
    @Qualifier("dataNucleusDaoImpl")
    private DataNucleusDao dataNucleusDao;
    @Test
    public void selectByPrimaryKey() throws Exception {
        Login login = dataNucleusDao.selectByPrimaryKey(Login.class, 1);
        System.out.println(login);
    }

    @Test
    public void selectByQuery() throws Exception {
//        List<Login> logins = dataNucleusDao.selectByQuery(Login.class, "username=='hcx'");
//        for (Login login:logins){
//            System.out.println(login);
//        }
    }

    @Test
    public void insert() throws Exception {
//        Login login=new Login();
//        login.setId(12);
//        login.setPassword("123");
//        login.setUsername("cpt");
//        Object insert = dataNucleusDao.insert(login);
//        System.out.println(insert);
    }

    @Test
    public void update() throws Exception {

//        boolean update = dataNucleusDao.update(null);
//        System.out.println(update);
//
        Login login=dataNucleusDao.selectByPrimaryKey(Login.class,45);
        login.setPassword("2222");
        boolean update = dataNucleusDao.update(login);
        System.out.println(update);
    }

    @Test
    public void deleteByPrimaryKey() throws Exception {
//        boolean b = dataNucleusDao.deleteByPrimaryKey(Login.class, 47);
//        System.out.println(b);
    }
    @Test
    public void callProc() throws Exception {
//        Map<String,Object> params=new HashMap<>();
//        params.put("v_uid",46);
//        params.put("v_movie_id",1);
//        params.put("v_num",1);
//        params.put("r_result",0);
//        Object snop_proc = dataNucleusDao.callProc("snop_proc", params);
//        System.out.println("snop_proc:"+snop_proc);
    }
}