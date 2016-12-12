package com.cpt.movie.service;

import com.cpt.movie.dto.SnapMessageDto;

/**
 * 抢购服务层接口
 * Created by cpt72 on 2016/12/11.
 */
public interface SnapService {

    /**
     * 抢购方法
     * 会开启事务，先减去库存，再添加抢购记录
     * @param uid 用户编号
     * @param movieId 电影票编号
     * @return 抢购状态
     */
    SnapMessageDto snapMovie(int uid, int movieId);

    /**
     * 抢购方法
     * 会开启事务，先减去库存，再添加抢购记录
     * @param uid 用户编号
     * @param movieId 电影票编号
     * @param num 抢购数量
     * @return 抢购状态
     */
    SnapMessageDto snapMovie(int uid,int movieId,int num);

}
