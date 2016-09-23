package com.panlingxiao.shiro.example2.service;

import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.entity.Role;
import com.panlingxiao.shiro.example2.entity.RoleResource;
import com.panlingxiao.shiro.example2.entity.UserRole;

import java.util.List;



public interface RoleService {
	public void add(Role role);
	
	public void delete(int id);
	
	public Role load(int id);
	
	public List<Role> list();
	
	public void update(Role role);
	
	public List<Role> listRole();
	
	
	public UserRole loadUserRole(int uid, int roleId);
	
	public void addUserRole(int uid, int roleId);
	
	public void deleteUserRole(int uid, int roleId);
	
	/**
	 * 删除某个用户的所有角色
	 * @param uid
	 */
	public void deleteUserRoles(int uid);
	/**
	 * 根据角色id获取可以访问的所有资源
	 * @param roleId
	 * @return
	 */
	public List<Resource> listRoleResource(int roleId);
	
	public void addRoleResource(int roleId, int resId);
	
	public void deleteRoleResource(int roleId, int resId);
	
	public RoleResource loadResourceRole(int roleId, int resId);
}
