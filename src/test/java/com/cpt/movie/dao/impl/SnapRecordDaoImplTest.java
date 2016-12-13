package com.cpt.movie.dao.impl;

import com.cpt.movie.dao.SnapRecordDao;
import com.cpt.movie.pojo.SnapRecord;
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
public class SnapRecordDaoImplTest {

    @Autowired
    @Qualifier("snapRecordDaoImpl")
    private SnapRecordDao snapRecordDao;
    @Test
    public void insert() throws Exception {
//        SnapRecord snapRecord=new SnapRecord();
//        snapRecord.setUid(45);
//        snapRecord.setMovieId(1);
//        int id=snapRecordDao.insert(snapRecord);
//        System.out.println(id);

    }

    @Test
    public void update() throws Exception {
//        SnapRecord snapRecord =snapRecordDao.selectById(33);
//        System.out.println(snapRecord);
//        snapRecord.setNum(1);
//        System.out.println(snapRecordDao.update(snapRecord));
    }

    @Test
    public void selectById() throws Exception {
        SnapRecord snapRecord =snapRecordDao.selectById(33);
        System.out.println(snapRecord);
    }

    @Test
    public void selectByUid() throws Exception {
        List<SnapRecord> snapRecords =snapRecordDao.selectByUid(1);
        for (SnapRecord snapRecord:snapRecords)
            System.out.println(snapRecord);
    }
    @Test
    public void selectByMovieId() throws Exception {
        List<SnapRecord> snapRecords =snapRecordDao.selectByMovieId(1);
        for (SnapRecord snapRecord:snapRecords)
            System.out.println(snapRecord);
    }
    @Test
    public void selectByUidAndMovieId() throws Exception {
        SnapRecord snapRecord =snapRecordDao.selectByUidAndMovieId(1,1);
        System.out.println(snapRecord);
    }
}