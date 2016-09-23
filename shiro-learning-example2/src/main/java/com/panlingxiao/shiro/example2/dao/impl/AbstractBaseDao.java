package com.panlingxiao.shiro.example2.dao.impl;

import com.panlingxiao.shiro.example2.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

/**
 * Created by panlingxiao on 2016/9/23.
 */
public abstract class AbstractBaseDao<T> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    public void add(T t){
        getSession().save(t);
    }

    public void update(T t){
        getSession().update(t);
    }

    public void delete(Integer id){
        T t = load(id);
        if(t != null){
            getSession().delete(t);
        }
    }

    @Override
    public T load(Integer id) {
        return (T) getSession().load(getClazz(),id);
    }


    public Class<T> getClazz() {
        if(clazz == null){
            ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
            clazz = (Class<T>) superclass.getActualTypeArguments()[0];

        }
        return clazz;
    }

}
