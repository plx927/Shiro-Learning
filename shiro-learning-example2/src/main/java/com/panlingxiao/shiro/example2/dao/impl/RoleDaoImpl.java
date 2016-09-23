package com.panlingxiao.shiro.example2.dao.impl;

import com.panlingxiao.shiro.example2.dao.RoleDao;
import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.entity.Role;
import com.panlingxiao.shiro.example2.entity.RoleResource;
import com.panlingxiao.shiro.example2.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by panlingxiao on 2016/9/23.
 */
@Repository("roleDao")
public class RoleDaoImpl extends AbstractBaseDao<Role> implements RoleDao {

    @Override
    public List<Role> listRole() {
        String hql = "select r from Role r";
        return getSession().createQuery(hql).list();
    }

    @Override
    public UserRole loadUserRole(int uid, int roleId) {
        String hql = "select ur from UserRole ur where ur.userId=? and ur.roleId=?";
        return (UserRole) getSession().createQuery(hql).setParameter(0, uid).setParameter(1, roleId).uniqueResult();
    }

    @Override
    public void addUserRole(int uid, int roleId) {
        UserRole ur = null;
        //判断用户角色是否存在
        ur = loadUserRole(uid, roleId);
        if (ur == null) {
            ur = new UserRole();
            ur.setRoleId(roleId);
            ur.setUserId(uid);
            this.getSession().save(ur);
        }
    }

    @Override
    public void deleteUserRole(int uid, int roleId) {
        UserRole ur = null;
        ur = loadUserRole(uid, roleId);
        if (ur != null) {
            this.getSession().delete(ur);
        }
    }

    @Override
    public void deleteUserRoles(int uid) {
        String hql = "delete UserRole ur where ur.userId=?";
        getSession().createQuery(hql).setParameter(0, uid).executeUpdate();
    }

    @Override
    public List<Resource> listRoleResource(int roleId) {
        String hql = "select res from Role role,Resource res,RoleResource rr where role.id=rr.roleId and res.id=rr.resId and role.id=?";
        return getSession().createQuery(hql).setParameter(0, roleId).list();
    }

    @Override
    public void addRoleResource(int roleId, int resId) {
        RoleResource rr = null;
        rr = loadResourceRole(roleId, resId);
        if (rr == null) {
            rr = new RoleResource();
            rr.setResId(resId);
            rr.setRoleId(roleId);
            this.getSession().save(rr);
        }
    }

    @Override
    public void deleteRoleResource(int roleId, int resId) {
        RoleResource rr = null;
        rr = loadResourceRole(roleId, resId);
        if (rr != null) {
            this.getSession().delete(rr);
        }
    }

    @Override
    public RoleResource loadResourceRole(int roleId, int resId) {
        String hql = "select rr from RoleResource rr where rr.roleId=? and rr.resId=?";
        return (RoleResource) getSession().createQuery(hql).setParameter(0, roleId).setParameter(1, resId).uniqueResult();
    }
}
