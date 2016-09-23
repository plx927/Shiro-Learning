package com.panlingxiao.shiro.example2.dao.impl;

import com.panlingxiao.shiro.example2.dao.UserDao;
import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.entity.Role;
import com.panlingxiao.shiro.example2.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by panlingxiao on 2016/9/23.
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractBaseDao<User> implements UserDao {

    @Override
    public List<User> listUser() {
        String hql = "select u from User u";
        return getSession().createQuery(hql).list();
    }

    @Override
    public User loadByUsername(String username) {
        String hql = "select u from User u where u.username=?";
        return (User) getSession().createQuery(hql).setParameter(0, username).uniqueResult();
    }

    @Override
    public List<User> listUserByRoleId(int roleId) {
        String hql = "select u from UserRole ur,User u where ur.roleId=? and ur.userId=u.Id ";
        return getSession().createQuery(hql).setParameter(0, roleId).list();
    }

    @Override
    public List<Resource> listAllResourceByUserId(int uid) {
        String hql = "select res from User u,UserRole ur,Resource res,RoleResource rr where  u.id=ur.userId and ur.roleId=rr.roleId  and rr.resId=res.id and u.id=?";
        return getSession().createQuery(hql).setParameter(0,uid).list();
    }

    @Override
    public List<String> listRoleNameByUser(int uid) {
        String hql = "select r.name from UserRole ur,Role r where ur.userId=? and ur.roleId=r.id";
        return getSession().createQuery(hql).setParameter(0,uid).list();
    }

    @Override
    public List<Role> listRoleByUserId(int uid) {
        String hql = "select r from UserRole ur,Role r where ur.userId=? and ur.roleId=r.id ";
        return getSession().createQuery(hql).setParameter(0, uid).list();
    }


}
