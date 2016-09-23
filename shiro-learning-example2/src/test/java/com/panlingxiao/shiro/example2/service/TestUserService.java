package com.panlingxiao.shiro.example2.service;


import com.panlingxiao.shiro.example2.entity.User;
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
public class TestUserService {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserService userService;

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
	public void testAdd() {
		User u1 = new User();
		u1.setUsername("lisi");
		u1.setNickname("李四");
		u1.setPassword("123");
		userService.add(u1);

		User u2 = new User();
		u2.setUsername("admin");
		u2.setNickname("管理员");
		u2.setPassword("123");
		userService.add(u2);

		User u3 = new User();
		u3.setUsername("zs");
		u3.setNickname("张三");
		u3.setPassword("123");
		userService.add(u3);
	}
	

	
}
