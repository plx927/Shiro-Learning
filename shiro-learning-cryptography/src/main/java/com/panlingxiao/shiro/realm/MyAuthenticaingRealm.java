package com.panlingxiao.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * Created by panlingxiao on 2016/9/23.
 */
public class MyAuthenticaingRealm extends AuthenticatingRealm{
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Object principal = token.getPrincipal();
        if("hello".equals(principal)){
            //使用Principal作为salt值来进行加密,模拟从数据库中取出数据
            String credential = new Md5Hash("123",principal).toHex();
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,credential,getName());
            //设置salt值
            simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(principal));
            return simpleAuthenticationInfo;
        }
        throw new AuthenticationException("用户名或者密码错误");
    }
}
