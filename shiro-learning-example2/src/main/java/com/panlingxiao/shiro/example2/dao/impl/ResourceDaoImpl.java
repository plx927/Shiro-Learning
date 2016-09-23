package com.panlingxiao.shiro.example2.dao.impl;

import com.panlingxiao.shiro.example2.dao.ResourceDao;
import com.panlingxiao.shiro.example2.entity.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by panlingxiao on 2016/9/23.
 */
@Repository("resourceDao")
public class ResourceDaoImpl extends AbstractBaseDao<Resource> implements ResourceDao{
    @Override
    public List<Resource> listResource() {
        String hql = "select res from Resource res ";
        return getSession().createQuery(hql).list();
    }
}
