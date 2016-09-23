package com.panlingxiao.shiro.example2.dao;

import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.entity.Role;
import com.panlingxiao.shiro.example2.entity.User;

import java.util.List;


public interface UserDao extends BaseDao<User>{
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> listUser();

	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	public User loadByUsername(String username);

	/**
	 * 根据角色ID查询用户
	 * @param roleId
	 * @return
	 */
	public List<User> listUserByRoleId(int roleId);

	/**
	 * 根据用户ID查询用户所能访问的资源
	 * @param uid
	 * @return
	 */
	public List<Resource> listAllResourceByUserId(int uid);

	/**
	 * 根据用户ID查询角色的名字
	 * @param uid
	 * @return
	 */
	public List<String> listRoleNameByUser(int uid);

	/**
	 * 根据用户ID查询角色
	 * @param uid
	 * @return
	 */
	public List<Role> listRoleByUserId(int uid);
	
}
