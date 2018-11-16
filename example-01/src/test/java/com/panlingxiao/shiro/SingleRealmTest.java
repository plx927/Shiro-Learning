package com.panlingxiao.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class SingleRealmTest {
    private SecurityManager securityManager;

    @Before
    public void setUp(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:singleRealm.ini");
        securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void authenticateSuccess(){
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("admin","123"));
        Assert.assertEquals("admin",subject.getPrincipal());
        Assert.assertEquals(1,subject.getPrincipals().asList().size());
        System.out.println(subject.isPermitted("abc"));
        subject.logout();
    }

    @Test(expected = AuthenticationException.class)
    public void authenticateFail(){
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("admin","124"));
    }
}
