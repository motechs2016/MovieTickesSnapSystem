package lab.io.rush.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by cpt72 on 2016/12/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {
    @Autowired
    @Qualifier("emailServiceImpl")
    private EmailService emailService;
    @Test
    public void send() throws Exception {
//        for (int i=0;i<10;i++){
//        emailService.sendEmail("1178743742@qq.com","test","测试邮件");
//        Thread.sleep(1000);
//        }
    }

}