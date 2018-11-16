package com.panlingxiao.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

public class MultiRealmTest {
    private SecurityManager securityManager;
    @Before
    public void setUp(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:multiRealm.ini");
        securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void authenticateSuccess(){
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("admin","123"));
        System.out.println(subject.getPrincipal());
        System.out.println(subject.getPrincipals());
        subject.logout();
    }
}
