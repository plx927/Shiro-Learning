package com.panlingxiao.shiro.authorization;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

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

        //判断权限
        if(subject.isPermitted("user:add")){
            log.info("授权成功,subject has permit user:add");
        }

        if(subject.isPermitted(new WildcardPermission("user:get"))){
            log.info("授权成功,subject has permit user:get");
        }

        //domain:action --> user:update,user:delete
        if(subject.isPermitted("user:update,delete")){
            log.info("授权成功,subject has permit user:update,delete");
        }

        WildcardPermissionResolver resolver = new WildcardPermissionResolver();

        if(subject.isPermittedAll(Arrays.asList(resolver.resolvePermission("user:update"),resolver.resolvePermission("user:delete")))){
            log.info("批量测试授权");
        }

        if(!subject.isPermitted(new WildcardPermission("admin:get"))){
            log.info("授权失败,subject has not permit admin:get");
        }

        subject.logout();
    }
}
