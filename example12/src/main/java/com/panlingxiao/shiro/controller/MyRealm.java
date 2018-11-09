package com.panlingxiao.shiro.controller;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm implements Realm {
    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken ut = (UsernamePasswordToken) token;
        if("admin".equals(ut.getUsername()) && "123".equals(new String((char[]) ut.getCredentials()))){
            return new SimpleAuthenticationInfo(new User("admin"),"123",getName());
        }
        return null;
    }
}
