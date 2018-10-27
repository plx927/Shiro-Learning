package com.panlingxiao.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * Created by panlingxiao on 2016/9/23.
 */
public class MyAuthenticaingRealm2 extends AuthenticatingRealm{


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Object principal = token.getPrincipal();
        DefaultPasswordService passwordService = new DefaultPasswordService();

        if("hello".equals(principal)){
            //将凭证进行加密,模拟从数据库中取出的数据
            String credential = passwordService.encryptPassword(token.getCredentials());
            //后面会自动通过PasswordMatcher来做凭证的校验
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,credential,getName());
            return simpleAuthenticationInfo;
        }
        throw new AuthenticationException("用户名或者密码错误");
    }
}
