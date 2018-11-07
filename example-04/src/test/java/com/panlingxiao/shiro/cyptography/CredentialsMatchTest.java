package com.panlingxiao.shiro.cyptography;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by panlingxiao on 2016/9/23.
 */
public class CredentialsMatchTest {

    /**
     * 测试当认证返回的认证信息中的凭证和用户指定的凭证不一致时，会抛出异常
     * 因为AuthenticatingRealm在用户成功返回完认证信息之后，依旧会对用户返回的Credentials与实际传入的Credentials做比较
     */
    @Test(expected = IncorrectCredentialsException.class)
    public void testSimpleCredentialMatcher() {
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-simple-credential.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("hello", "1234");

        subject.login(token);
    }

    /**
     * 将用户密码使用MD5加密存储，设置指定CredentialMatcher来完成用户凭证的校验
     * 测试使用HashCredentialMatcher
     */
    @Test
    public void testHashCredentialMatcher() {
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-hash-credential.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("hello", "123");
        subject.login(token);
    }


    @Test
    public void testHashCredentialMatcherBySalt() {
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-hash-salt-credential.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("hello", "123");
        subject.login(token);
    }


    @Test
    public void testPasswordMatcher() {
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-password-credential.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("hello", "123");
        subject.login(token);
    }

}
