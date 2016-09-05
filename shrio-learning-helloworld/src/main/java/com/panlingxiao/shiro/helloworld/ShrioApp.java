package com.panlingxiao.shiro.helloworld;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by panlingxiao on 2016/9/2.
 */
public class ShrioApp {


    private static final Logger log = LoggerFactory.getLogger(ShrioApp.class);

    public static void main(String[] args) {
        /*
         * 构建 Shiro 环境
         */
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        //设置SecurityManager
        SecurityUtils.setSecurityManager(securityManager);

        //获取当前用户
        // A Subject is just a security-specific "view" of an application User.
        Subject currentUser = SecurityUtils.getSubject();

        Session session = currentUser.getSession();
        session.setAttribute("hello", "吴大神V5");

        //判断用户是否认证
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("吴大神", "吴大神");
            token.setRememberMe(true);
            //用户登入
            try {
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                log.info("用户名不存在");
            } catch (IncorrectCredentialsException e) {
                log.info("密码错误");
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        }

        //判断用户的角色
        if(currentUser.hasAllRoles(Arrays.asList("superman","superdashen"))){
            log.info("大神拥有的角色,大神V5");
        }else{
            log.info("大神没有所有的角色");
        }
        //判断权限
        if(currentUser.isPermittedAll("meimei:delete:1","product:get:*","product:add","product:list","product:nimei")){
            log.info("大神V5，拥有所有权限");
        }

        log.info("大神的Session,hello:{}",session.getAttribute("hello"));
        currentUser.logout();




       //log.info("大神的Session,hello:{}",session.getAttribute("hello"));

    }
}
