package com.panlingxiao.shiro.realm.service;

import com.panlingxiao.shiro.realm.entity.Permission;

/**
 *
 */
public interface PermissionService {
    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);
}
