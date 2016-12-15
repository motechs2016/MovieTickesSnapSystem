package com.cpt.movie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


public class MovieTickesSnapSystemApplicationTests {

	@Test
	public void contextLoads() {
		String content = "%s,您于%s参与%s电影票抢购活动，成功抢购电影票%s张，感谢您的参与!";
		System.out.println(content);
		content=String.format(content,"11787@qq.com",new Date(),"你的名字",1);
		System.out.println(content);
	}

}
