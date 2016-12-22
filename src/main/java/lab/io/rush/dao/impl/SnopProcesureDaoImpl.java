package lab.io.rush.dao.impl;

import lab.io.rush.dao.SnopProcesureDao;
import lab.io.rush.dao.datanucleus.dao.DataNucleusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cpt72 on 2016/12/12.
 */
@Repository
public class SnopProcesureDaoImpl implements SnopProcesureDao {
    @Autowired
    @Qualifier("dataNucleusDaoImpl")
    private DataNucleusDao dataNucleusDao;

    @Override
    public int callSnopProc(int uid, int movieId, int num) {
        Map<String, Object> params = new HashMap<>();
        params.put("v_uid", uid);
        params.put("v_movie_id", movieId);
        params.put("v_num", num);
        params.put("r_result", 0);
        Object snop_proc = dataNucleusDao.callProc("snop_proc", params);
        if (snop_proc == null)
            return 0;
        else
            return (int) snop_proc;
    }
}
