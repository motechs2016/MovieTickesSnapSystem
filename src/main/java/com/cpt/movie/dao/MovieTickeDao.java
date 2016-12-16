package com.cpt.movie.dao;

import com.cpt.movie.pojo.MovieTicke;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 电影票表持久层
 * Created by cpt72 on 2016/12/11.
 */
public interface MovieTickeDao {

    /**
     * 插入电影票
     *
     * @param movieTicke 电影票对象
     * @return 电影票编号
     */
    int insert(MovieTicke movieTicke);

    /**
     * 更新电影票
     *
     * @param movieTicke 电影票对象
     * @return 更新状态
     */
    @CacheEvict(value = "MovieTicke", key = "'MovieTicke-id:'+#movieTicke.id", beforeInvocation = true)
    boolean update(MovieTicke movieTicke);

    /**
     * 根据编号选择电影票
     *
     * @param id 电影票编号
     * @return 电影票对象
     */
    @Cacheable(value = "MovieTicke", key = "'MovieTicke-id:'+#id")
    MovieTicke selectById(int id);

    /**
     * 获取所有电影票
     *
     * @return 电影票对象列表
     */
    List<MovieTicke> selectAll();

    /**
     * 获取电影票数量信息
     * @param id 电影票编号
     * @return 数量
     */
    @Cacheable(value = "MovieTickeNum", key = "'MovieTickeNum-id:'+#id")
    int getMovieTickeNum(int id);
}
