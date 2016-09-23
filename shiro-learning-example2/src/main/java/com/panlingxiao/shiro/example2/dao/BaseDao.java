package com.panlingxiao.shiro.example2.dao;

/**
 * Created by panlingxiao on 2016/9/23.
 */
public interface BaseDao<T> {

    public void add(T t);

    public void update(T t);

    public void delete(Integer id);

    public T load(Integer id);


}
