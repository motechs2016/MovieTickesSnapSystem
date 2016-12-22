package lab.io.rush.dao.impl;

import lab.io.rush.dao.MovieTickeDao;
import lab.io.rush.dao.datanucleus.dao.DataNucleusDao;
import lab.io.rush.pojo.MovieTicke;
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
        return dataNucleusDao.selectByPrimaryKey(MovieTicke.class, id);
    }

    @Override
    public List<MovieTicke> selectAll() {
        return dataNucleusDao.selectByQuery(MovieTicke.class, "");
    }

    @Override
    public int getMovieTickeNum(int id) {
        MovieTicke ticke = dataNucleusDao.selectByPrimaryKey(MovieTicke.class, id);
        if (ticke==null)
            return 0;
        else
            return ticke.getNum();
    }

}
