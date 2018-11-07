package com.panlingxiao.shiro.authenticaion;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by panlingxiao on 2016/9/6.
 * 通过JdbcRealm来完成对数据认证
 */
public class AuthenticationByJdbcRealm {


    static final Logger log = LoggerFactory.getLogger(AuthenticationByJdbcRealm.class);

    public static void main(String[] args) {
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:authentication/shiro-jdbc.ini");
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);


        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
            currentUser.login(token);
            log.info("登入成功");
        }
        currentUser.logout();
    }
}
