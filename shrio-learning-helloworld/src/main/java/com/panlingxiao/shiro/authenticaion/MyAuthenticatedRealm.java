package com.panlingxiao.shiro.authenticaion;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * Created by panlingxiao on 2016/9/6.
 * 实现一个认证的Realm
 */
public class MyAuthenticatedRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if(token instanceof UsernamePasswordToken){
            UsernamePasswordToken upt = (UsernamePasswordToken) token;
            if("zs".equals(upt.getPrincipal()) && "123".equals(new String(upt.getPassword()))){
                return new SimpleAccount("zs","123","");
            }
        }
        return null;
    }
}
