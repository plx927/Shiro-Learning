package com.panlingxiao.shiro.authenticaion;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by panlingxiao on 2016/9/6.
 * 测试自定义的Realm
 */
public class AuthenticationApp {


    static final Logger log = LoggerFactory.getLogger(AuthenticationApp.class);

    public static void main(String[] args) {
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:authentication/shiro-authentication.ini");

        //全局唯一
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();


        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();


        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken();
            token.setUsername("zs");
            token.setPassword("123".toCharArray());
            try {
                /*
                 * 1.subject底层委派SecurityManager执行真正的认证操作
                 * 2.SecurityManager作为应用的保护伞，使用其内部的Authenticator来完成用户token的认证
                 *  默认使用的Authenticator为ModularRealmAuthenticator
                 * 3.ModularRealmAuthenticator管理多个Realm,Realm查询数据与传递的token做比较
                 */
                subject.login(token);
                log.info("认证成功");
                log.info("user's principal is :{},RealmNames is:{}",subject.getPrincipals(),subject.getPrincipals().getRealmNames());
            } catch (AuthenticationException e) {
                log.info("认证失败!");
            }
        }

        subject.logout();


    }
}
