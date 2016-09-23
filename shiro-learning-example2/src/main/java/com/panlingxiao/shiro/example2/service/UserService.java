package com.panlingxiao.shiro.example2.service;

import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.entity.Role;
import com.panlingxiao.shiro.example2.entity.User;

import java.util.List;


public interface UserService {
	public User add(User user);
	
	public User add(User user, List<Integer> rids);
	
	public void delete(int id);
	
	public User update(User user, List<Integer> rids);
	
	public User update(User user);
	
	public User load(int id);
	
	public User loadByUsername(String username);
	
	public User login(String username, String password);
	
	public List<User> list();
	
	public List<User> listByRole(int id);
	
	public List<Resource> listAllResource(int uid);
	
	public List<String> listRoleNameByUser(int uid);
	
	public List<Role> listRoleByUserId(int uid);
}
