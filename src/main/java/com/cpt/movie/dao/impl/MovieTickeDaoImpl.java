package com.cpt.movie.dao.impl;

import com.cpt.movie.dao.MovieTickeDao;
import com.cpt.movie.dao.datanucleus.dao.DataNucleusDao;
import com.cpt.movie.pojo.MovieTicke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cpt72 on 2016/12/11.
 */
@Repository
public class MovieTickeDaoImpl implements MovieTickeDao {
    @Autowired
    @Qualifier("dataNucleusDaoImpl")
    private DataNucleusDao dataNucleusDao;
    @Override
    public int insert(MovieTicke movieTicke) {
        return (int) dataNucleusDao.insert(movieTicke);
    }

    @Override
    public boolean update(MovieTicke movieTicke) {
        return dataNucleusDao.update(movieTicke);
    }

    @Override
    public MovieTicke selectById(int id) {
        return dataNucleusDao.selectByPrimaryKey(MovieTicke.class,id);
    }

}
