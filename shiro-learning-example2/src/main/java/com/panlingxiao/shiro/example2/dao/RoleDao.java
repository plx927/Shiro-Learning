package com.panlingxiao.shiro.example2.dao;

import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.entity.Role;
import com.panlingxiao.shiro.example2.entity.RoleResource;
import com.panlingxiao.shiro.example2.entity.UserRole;

import java.util.List;



public interface RoleDao extends BaseDao<Role>{

	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> listRole();

	/**
	 * 根据用户ID和角色ID查询UserRole
	 * @param uid 用户ID
	 * @param roleId 角色ID
	 * @return
	 */
	public UserRole loadUserRole(int uid, int roleId);

	/**
	 * 为某个用户添加特定的角色
	 * @param uid
	 * @param roleId
	 */
	public void addUserRole(int uid, int roleId);

	/**
	 * 删除某个用户特定的角色
	 * @param uid
	 * @param roleId
	 */
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

	/**
	 * 为某个角色添加资源
	 * @param roleId
	 * @param resId
	 */
	public void addRoleResource(int roleId, int resId);

	/**
	 * 删除某个角色所能访问的资源
	 * @param roleId
	 * @param resId
	 */
	public void deleteRoleResource(int roleId, int resId);

	/**
	 * 根据角色ID和资源ID查询角色资源
	 * @param roleId
	 * @param resId
	 * @return
	 */
	public RoleResource loadResourceRole(int roleId, int resId);

	void deleteRoleResourceByRoleId(Integer id);
}
