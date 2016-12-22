package lab.io.rush.dao.datanucleus.dao;

import java.util.List;
import java.util.Map;

/**
 * DataNucleus持久层接口
 * <p>
 * Created by cpt72 on 2016/12/11.
 */
public interface DataNucleusDao {

    /**
     * 根据主键获取数据
     *
     * @param var 数据实体类型class
     * @param key 主键
     * @param <T> 数据实体类型
     * @return 查询到的记录，没有记录为null
     */
    <T> T selectByPrimaryKey(Class<T> var, Object key);

    /**
     * 书写sql查询数据
     *
     * @param var   数据实体类型class
     * @param query sql语句
     * @param <T>   数据实体类型
     * @return 查询到的记录列表，没有记录为null
     */
    <T> List<T> selectByQuery(Class<T> var, String query);

    /**
     * 插入数据
     *
     * @param object 数据对象
     * @param <T>    数据类型
     * @return 插入后的数据主键，失败返回null
     */
    <T> Object insert(T object);

    /**
     * 更新数据
     *
     * @param object 数据对象 需要包含主键
     * @param <T>    数据类型
     * @return 插入状态
     */
    <T> boolean update(T object);

    /**
     * 根据主键删除数据
     *
     * @param var 数据实体类型class
     * @param key 主键对象
     * @param <T> 数据类型
     * @return 删除状态
     */
    <T> boolean deleteByPrimaryKey(Class<T> var, Object key);

    /**
     * 调用存储过程
     *
     * @param query  存储过程名称
     * @param params 存储过程参数 只能包含r_result一个返回参数
     * @return 查询到的记录列表，没有记录为null
     */
    Object callProc(String query, Map<String, Object> params);

}
