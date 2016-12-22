package lab.io.rush.dao.impl;

import lab.io.rush.dao.LoginDao;
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
public class LoginDaoImplTest {

    @Autowired
    @Qualifier("loginDaoImpl")
    private LoginDao loginDao;
    @Test
    public void insert() throws Exception {
//        Login login=new Login();
//        login.setUsername("hcx");
//        int id=loginDao.insert(login);
//        System.out.println(id);

    }

    @Test
    public void update() throws Exception {
//        Login login =loginDao.selectById(45);
//        System.out.println(login);
//        login.setPassword("12345");
//        System.out.println(loginDao.update(login));
    }

    @Test
    public void selectById() throws Exception {
        Login login =loginDao.selectById(45);
        System.out.println(login);

    }

    @Test
    public void selectByUsername() throws Exception {
        Login login =loginDao.selectByUsername("hcx");
        System.out.println(login);
    }
    @Test
    public void selectByEmail() throws Exception {
        Login login =loginDao.selectByEmail("cpt725@qq.com");
        System.out.println(login);
    }
}