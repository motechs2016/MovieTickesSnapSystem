package com.cpt.movie.dao.datanucleus.dao.impl;

import com.cpt.movie.dao.datanucleus.dao.DataNucleusDao;
import com.cpt.movie.log.AutoLogger;
import org.datanucleus.api.jdo.JDOQuery;
import org.datanucleus.metadata.StoredProcQueryParameterMode;
import org.datanucleus.store.rdbms.query.StoredProcedureQuery;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.jdo.*;
import javax.jdo.annotations.PrimaryKey;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Created by cpt72 on 2016/12/11.
 */
@Repository
public class DataNucleusDaoImpl implements DataNucleusDao {
    @AutoLogger
    private Logger logger;
    @Autowired
    private PersistenceManagerFactory persistenceManagerFactory;

    @Override
    public <T> T selectByPrimaryKey(Class<T> var, Object key) {
        PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();

        T result = null;
        try {
            result = pm.getObjectById(var, key);
        } catch (JDOObjectNotFoundException e) {
            logger.warn("DataNucleusDaoImpl.selectByPrimaryKey key=" + key + " \n" + e.getMessage());
        } catch (Exception e) {
            logger.error("DataNucleusDaoImpl.selectByPrimaryKey error:key=" + key + " \n" + e.getMessage(), e);
        } finally {
            pm.close();
        }
        return result;
    }

    @Override
    public <T> List<T> selectByQuery(Class<T> var, String query) {

        PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
        List<T> list = null;
        try {
            Query q = pm.newQuery(var, query);
            list = (List<T>) q.execute();
        } catch (JDOObjectNotFoundException e) {
            logger.warn("DataNucleusDaoImpl.selectByQuery query=" + query + " \n" + e.getMessage());
        } catch (Exception e) {
            logger.error("DataNucleusDaoImpl.selectByQuery error:query=" + query + " \n" + e.getMessage(), e);
        } finally {
            pm.close();
        }
        return list;
    }

    @Override
    public <T> Object insert(T object) {
        PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        Object id = null;
        try {
            tx.begin();
            pm.makePersistent(object);
            tx.commit();
            id = getObjectId(object);
        } catch (Exception e) {
            logger.error("DataNucleusDaoImpl.insert error:object=" + object + " \n" + e.getMessage(), e);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
        return id;
    }

    @Override
    public <T> boolean update(T object) {
        boolean flag = false;
        PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Object temp = pm.getObjectById((Class<T>) object.getClass(), getObjectId(object));
            if (temp == null)
                return false;
            BeanUtils.copyProperties(object, temp);
            tx.commit();
            flag = true;
        } catch (JDOObjectNotFoundException e) {
            logger.warn("DataNucleusDaoImpl.update object=" + object + " \n" + e.getMessage());
        } catch (Exception e) {
            logger.error("DataNucleusDaoImpl.update error:object=" + object + " \n" + e.getMessage(), e);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
        return flag;
    }


    @Override
    public <T> boolean deleteByPrimaryKey(Class<T> var, Object key) {
        boolean flag = false;
        PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            Object temp = pm.getObjectById(var, key);
            if (temp == null)
                return false;
            pm.deletePersistent(temp);
            tx.commit();
            flag = true;
        } catch (JDOObjectNotFoundException e) {
            logger.warn("DataNucleusDaoImpl.deleteByPrimaryKey key=" + key + " \n" + e.getMessage());
        } catch (Exception e) {
            logger.error("DataNucleusDaoImpl.deleteByPrimaryKey error:key=" + key + " \n" + e.getMessage(), e);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
        return flag;
    }

    @Override
    public Object callProc(String query, Map<String, Object> params) {
        PersistenceManager pm = persistenceManagerFactory.getPersistenceManager();
        Object result = null;
        try {
            Query q = pm.newQuery("STOREDPROC", query);
            StoredProcedureQuery spq = (StoredProcedureQuery) ((JDOQuery) q).getInternalQuery();
            for (String key : params.keySet()) {
                if (key.contains("result"))
                    spq.registerParameter(key, params.get(key).getClass(), StoredProcQueryParameterMode.OUT);
                else
                    spq.registerParameter(key, params.get(key).getClass(), StoredProcQueryParameterMode.IN);
            }
            boolean isresult = (boolean) spq.executeWithMap(params);
            result = spq.getOutputParameterValue("r_result");
        } catch (JDOObjectNotFoundException e) {
            logger.warn("DataNucleusDaoImpl.selectByQuery query=" + query + " \n" + e.getMessage());
        } catch (Exception e) {
            logger.error("DataNucleusDaoImpl.selectByQuery error:query=" + query + " \n" + e.getMessage(), e);
        } finally {
            pm.close();
        }
        return result;
    }

    /**
     * 获取对象主键值
     *
     * @param object 数据对象
     * @return 主键对象
     * @throws IllegalAccessException 非法访问异常
     */
    private Object getObjectId(Object object) throws IllegalAccessException {

        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            PrimaryKey annotation = field.getAnnotation(PrimaryKey.class);
            if (annotation != null) {
                field.setAccessible(true);
                return field.get(object);
            }
        }
        return null;
    }

}
