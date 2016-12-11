package com.cpt.movie.dao;

import com.cpt.movie.pojo.Login;

/**
 * 用户表持久层
 * Created by cpt72 on 2016/12/11.
 */
public interface LoginDao {

    /**
     * 插入用户
     * @param login 用户对象
     * @return 用户编号
     */
    int insert(Login login);

    /**
     * 更新用户
     * @param login 用户对象
     * @return 更新状态
     */
    boolean update(Login login);

    /**
     * 根据编号选择用户
     * @param id 用户编号
     * @return 用户对象
     */
    Login selectById(int id);

    /**根据用户名选择用户
     * @param username 用户名
     * @return 用户对象
     */
    Login selectByUsername(String username);

    /**根据电子邮箱选择用户
     * @param email 电子邮箱
     * @return 用户对象
     */
    Login selectByEmail(String email);
}
