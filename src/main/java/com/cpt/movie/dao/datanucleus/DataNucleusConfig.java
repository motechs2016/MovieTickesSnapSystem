package com.cpt.movie.dao.datanucleus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * DataNucleus持久层配置文件
 * <p>
 * Created by cpt72 on 2016/12/11.
 */
@Configuration
public class DataNucleusConfig {

    /**
     * 产生PersistenceManagerFactory Bean
     *
     * @return PersistenceManagerFactory对象
     */
    @Bean
    public PersistenceManagerFactory getPersistenceManagerFactory() {
        return JDOHelper.getPersistenceManagerFactory("Tutorial");
    }

}
