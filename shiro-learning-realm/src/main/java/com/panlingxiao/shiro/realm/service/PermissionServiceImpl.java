package com.panlingxiao.shiro.realm.service;

import com.panlingxiao.shiro.realm.dao.PermissionDao;
import com.panlingxiao.shiro.realm.dao.PermissionDaoImpl;
import com.panlingxiao.shiro.realm.entity.Permission;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
