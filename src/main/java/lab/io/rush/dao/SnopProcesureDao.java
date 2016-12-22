package lab.io.rush.dao;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * 抢购事务持久层
 * Created by cpt72 on 2016/12/12.
 */
public interface SnopProcesureDao {
    /**
     * 调用抢购存储过程
     *
     * @param uid     用户编号
     * @param movieId 电影票编号
     * @param num     抢购数量
     * @return 抢购状态
     */
//    @CacheEvict(value = "MovieTicke", key = "'MovieTicke-id:'+#movieId", beforeInvocation = true)
    int callSnopProc(int uid, int movieId, int num);
}
