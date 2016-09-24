package com.panlingxiao.shiro.example2.service;


import com.panlingxiao.shiro.example2.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class TestRoleService {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleService roleService;
	
	@Before
	public void setUp() {
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
	}
	
	@After
	public void tearDown() {
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession(); 
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
	}

	@Test
	public void testAddUserRole() {


		Role r2 = new Role();
		r2.setName("研发组");
		roleService.add(r2);

		Role r = new Role();
		r.setName("测试组");
		roleService.add(r);

		Role r3 = new Role();
		r2.setName("运维组");
		roleService.add(r3);


		roleService.addUserRole(1, 2);
		roleService.addUserRole(2, 1);
		roleService.addUserRole(3, 2);
		roleService.addUserRole(3, 3);


		roleService.addRoleResource(1, 1);
		roleService.addRoleResource(2, 2);
		roleService.addRoleResource(2, 3);
		roleService.addRoleResource(2, 4);

		roleService.addRoleResource(3, 5);
		roleService.addRoleResource(3, 6);
		roleService.addRoleResource(3, 7);
	}
}
