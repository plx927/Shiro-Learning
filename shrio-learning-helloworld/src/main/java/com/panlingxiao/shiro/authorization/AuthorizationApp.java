package com.panlingxiao.shiro.authorization;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by panlingxiao on 2016/9/13.
 */
public class AuthorizationApp {

    static final Logger log = LoggerFactory.getLogger(AuthorizationApp.class);

    public static void main(String[] args) {

        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:authorization/shiro-authorization.ini");
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken("hello","123");
            subject.login(token);
            log.info("认证成功,principalCollection is {}",subject.getPrincipals());
        }

        //判断用户的角色
        if(subject.hasRole("user")){
            log.info("授权成功，subject has user role");
        }

        if(subject.hasRole("admin")){
            log.info("授权成功，subject has admin role");
        }

        if(!subject.hasRole("visitor")){
            log.info("授权失败，subject has not visitor role");
        }



        subject.logout();
    }
}
