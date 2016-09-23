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
		res.setName("系统管理");
		res.setUrl("/admin/**");
		resourceService.add(res);
		
		res = new Resource();
		res.setName("用户管理");
		res.setUrl("/admin/user/*");
		resourceService.add(res);
		
		res = new Resource();
		res.setName("用户添加");
		res.setUrl("/admin/user/add");
		resourceService.add(res);
		
		res = new Resource();
		res.setName("用户删除");
		res.setUrl("/admin/user/delete");
		resourceService.add(res);
		
		res = new Resource();
		res.setName("角色管理");
		res.setUrl("/admin/role/*");
		resourceService.add(res);
		
		res = new Resource();
		res.setName("角色添加");
		res.setUrl("/admin/role/add");
		resourceService.add(res);
		
		res = new Resource();
		res.setName("角色修改");
		res.setUrl("/admin/role/update");
		resourceService.add(res);
	}
}
