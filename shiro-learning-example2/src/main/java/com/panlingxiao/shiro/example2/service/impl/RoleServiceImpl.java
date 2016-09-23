package com.panlingxiao.shiro.example2.service.impl;

import com.panlingxiao.shiro.example2.dao.RoleDao;
import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.entity.Role;
import com.panlingxiao.shiro.example2.entity.RoleResource;
import com.panlingxiao.shiro.example2.entity.UserRole;
import com.panlingxiao.shiro.example2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public void add(Role role) {
        roleDao.add(role);
    }

    public void delete(int id) {
        roleDao.delete(id);
    }

    public Role load(int id) {
        return roleDao.load(id);
    }

    public List<Role> list() {
        return roleDao.listRole();
    }

    public void update(Role role) {
        roleDao.update(role);
    }

    public List<Role> listRole() {
        return roleDao.listRole();
    }


    public UserRole loadUserRole(int uid, int roleId) {
        return roleDao.loadUserRole(uid, roleId);
    }

    public void addUserRole(int uid, int roleId) {
        roleDao.addUserRole(uid, roleId);
    }

    public void deleteUserRole(int uid, int roleId) {
        roleDao.deleteUserRole(uid, roleId);
    }

    public void deleteUserRoles(int uid) {
        roleDao.deleteUserRoles(uid);
    }

    public List<Resource> listRoleResource(int roleId) {
        return roleDao.listRoleResource(roleId);
    }

    public void addRoleResource(int roleId, int resId) {
        roleDao.addRoleResource(roleId, resId);
    }

    public void deleteRoleResource(int roleId, int resId) {
        roleDao.deleteRoleResource(roleId, resId);
    }

    public RoleResource loadResourceRole(int roleId, int resId) {
        return roleDao.loadResourceRole(roleId, resId);
    }

}
