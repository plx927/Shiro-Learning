package com.panlingxiao.shiro.example2.service;


import com.panlingxiao.shiro.example2.entity.Resource;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class TestResourceService {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ResourceService resourceService;
	
	@Test
	public void testAdd() {
		
		Resource res = new Resource();
		res.setName("用户管理");
		res.setUrl("/admin/user/list");
		res.setPermission("admin:user:list");
		resourceService.add(res);
		
		res = new Resource();
		res.setName("用户添加");
		res.setUrl("/admin/user/add");
		res.setPermission("admin:user:add");
		resourceService.add(res);
		
		res = new Resource();
		res.setName("用户删除");
		res.setUrl("/admin/user/delete");
		res.setPermission("admin:user:delete");
		resourceService.add(res);
		
		res = new Resource();
		res.setName("角色管理");
		res.setUrl("/admin/role/list");
		res.setPermission("admin:role:list");
		resourceService.add(res);
		
		res = new Resource();
		res.setName("角色添加");
		res.setUrl("/admin/role/add");
		res.setPermission("admin:role:add");
		resourceService.add(res);
		
		res = new Resource();
		res.setName("角色修改");
		res.setUrl("/admin/role/update");
		res.setPermission("admin:role:update");
		resourceService.add(res);


		res = new Resource();
		res.setName("资源管理");
		res.setUrl("/admin/res/list");
		res.setPermission("admin:res:list");
		resourceService.add(res);


	}
}
