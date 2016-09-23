package com.panlingxiao.shiro.example2.service.impl;

import com.panlingxiao.shiro.example2.dao.RoleDao;
import com.panlingxiao.shiro.example2.dao.UserDao;
import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.entity.Role;
import com.panlingxiao.shiro.example2.entity.User;
import com.panlingxiao.shiro.example2.service.UserService;
import com.panlingxiao.shiro.example2.util.ShiroKit;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	
	public User add(User user) {
		if(ShiroKit.isEmpty(user.getUsername())||ShiroKit.isEmpty(user.getPassword())) {
			throw new RuntimeException("用户名或者密码不能为空！");
		}
		user.setPassword(ShiroKit.md5(user.getPassword(), user.getUsername()));
		userDao.add(user);
		return user;
	}

	public void delete(int id) {
		userDao.delete(id);
	}

	public User update(User user,List<Integer> rids) {
		roleDao.deleteUserRoles(user.getId());
		for(int rid:rids) {
			roleDao.addUserRole(user.getId(), rid);
		}
		userDao.update(user);
		return user;
	}
	
	public User update(User user) {
		userDao.update(user);
		return user;
	}

	public User load(int id) {
		return userDao.load(id);
	}

	public User loadByUsername(String username) {
		return userDao.loadByUsername(username);
	}

	public User login(String username, String password) {
		User u = userDao.loadByUsername(username);
		if(u==null) {
			throw new UnknownAccountException("用户名或者密码出错");
		}
		else if(!u.getPassword().equals(ShiroKit.md5(password, username))) {
			throw new IncorrectCredentialsException("用户名或者密码出错");
		}
		else if(u.getStatus()==1){
			throw new LockedAccountException("用户已经被锁定");
		}
		return u;
	}

	public List<User> list() {
		return userDao.listUser();
	}

	public List<User> listByRole(int roleId) {
		return userDao.listUserByRoleId(roleId);
	}

	public List<Resource> listAllResource(int uid) {
		return userDao.listAllResourceByUserId(uid);
	}

	public User add(User user, List<Integer> rids) {
		this.add(user);
		for(int rid:rids) {
			roleDao.addUserRole(user.getId(), rid);
		}
		return user;
	}

	@Override
	public List<String> listRoleNameByUser(int uid) {
		return userDao.listRoleNameByUser(uid);
	}

	@Override
	public List<Role> listRoleByUserId(int uid) {
		return userDao.listRoleByUserId(uid);
	}

}
