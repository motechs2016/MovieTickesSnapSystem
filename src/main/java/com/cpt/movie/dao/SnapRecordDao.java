package com.cpt.movie.dao;

import com.cpt.movie.pojo.SnapRecord;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;

/**
 * 抢购记录表持久层
 * Created by cpt72 on 2016/12/11.
 */
public interface SnapRecordDao {

    /**
     * 插入抢购记录
     * @param snapRecord 抢购记录对象
     * @return 抢购记录编号
     */
    int insert(SnapRecord snapRecord);

    /**
     * 更新抢购记录
     * @param snapRecord 抢购记录对象
     * @return 更新状态
     */
    // 更新缓存之前先删除
    @Caching(evict = {
            @CacheEvict(value = "SnapRecord", key = "'SnapRecord-id:'+#snapRecord.id", beforeInvocation = true),
            @CacheEvict(value = "SnapRecord", key = "'SnapRecord-uid-movieId:'+#snapRecord.uid+'-'+#snapRecord.movieId", beforeInvocation = true)
    })
    boolean update(SnapRecord snapRecord);

    /**
     * 根据编号选择抢购记录
     * @param id 抢购记录编号
     * @return 抢购记录对象
     */
    @Cacheable(value = "SnapRecord", key = "'SnapRecord-id:'+#id")
    SnapRecord selectById(int id);

    /**根据用户编号选择抢购记录
     * @param uid 用户编号
     * @return 抢购记录
     */
    List<SnapRecord> selectByUid(int uid);

    /**根据电影编号选择抢购记录
     * @param movieId 电影编号
     * @return 抢购记录
     */
    List<SnapRecord> selectByMovieId(int movieId);

    /**根据电影编号选择抢购记录
     * @param uid 用户编号
     * @param movieId 电影编号
     * @return 抢购记录
     */
    @Cacheable(value = "SnapRecord", key = "'SnapRecord-uid-movieId:'+#uid+'-'+#movieId") // 从数据库拿到结果存入缓存，下次直接拿缓存
    SnapRecord selectByUidAndMovieId(int uid,int movieId);
}
