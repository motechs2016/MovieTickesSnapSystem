package lab.io.rush.dao.impl;

import lab.io.rush.dao.MovieTickeDao;
import lab.io.rush.pojo.MovieTicke;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by cpt72 on 2016/12/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieTickeDaoImplTest {
    @Autowired
    @Qualifier("movieTickeDaoImpl")
    private MovieTickeDao movieTickeDao;
    @Test
    public void insert() throws Exception {
//        MovieTicke movieTicke=new MovieTicke();
//        movieTicke.setName("你的名字");
//        movieTicke.setNum(300);
//        int insert = movieTickeDao.insert(movieTicke);
//        System.out.println(insert);
    }

    @Test
    public void update() throws Exception {
//        MovieTicke movieTicke =movieTickeDao.selectById(1);
//        System.out.println(movieTicke);
//        movieTicke.setName("功夫2");
//        System.out.println(movieTickeDao.update(movieTicke));
    }

    @Test
    public void selectById() throws Exception {
        MovieTicke movieTicke =movieTickeDao.selectById(1);
        System.out.println(movieTicke.getName());
    }
    @Test
    public void selectAll() throws Exception {
        List<MovieTicke> movieTickes = movieTickeDao.selectAll();
        for (MovieTicke ticke:movieTickes){
            System.out.println(ticke);
        }
    }

}