package com.cpt.movie.dao;

import org.springframework.stereotype.Repository;

/**
 * 抢购事务持久层
 * Created by cpt72 on 2016/12/12.
 */
public interface SnopProcesureDao {
    /**
     * 调用抢购存储过程
     * @param uid 用户编号
     * @param movieId 电影票编号
     * @param num 抢购数量
     * @return 抢购状态
     */
    int callSnopProc(int uid,int movieId,int num);
}
