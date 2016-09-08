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
 * 使用多个Realms进行认证,并且测试认证策略
 * 注意FirstSuccessfulStrategy策略：只返回第一个成功认证的Realm所返回的认证信息
 * 分析PrincipalCollection的实现，理解Primary Principal是如何得来的。
 */
public class AuthenticationByMultiRealms {

    static final Logger log = LoggerFactory.getLogger(AuthenticationByMultiRealms.class);

    public static void main(String[] args) {
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:authentication/shiro-multi-realm.ini");
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("zs", "123");
            try {
                subject.login(token);
                log.info("认证成功");
                log.info("Principal is :{}，Realms is {},Primary Principal is:{}",
                        subject.getPrincipals(),
                        subject.getPrincipals().getRealmNames(),
                        subject.getPrincipals().getPrimaryPrincipal());

            } catch (AuthenticationException e) {
                log.error("认证失败");
            }
        }
    }
}
