package com.panlingxiao.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

import java.util.HashMap;
import java.util.Map;

public class MyRealm implements Realm {

    private static final Map<String,String>  USER_INFO = new HashMap<>();

    static {
        USER_INFO.put("admin","123");
    }


    @Override
    public String getName() {
        return "MyRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //return token instanceof UsernamePasswordToken;
        return UsernamePasswordToken.class.isAssignableFrom(token.getClass());
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upt = (UsernamePasswordToken) token;
        String principal = (String) upt.getPrincipal();
        String password = new String(upt.getPassword());
        String val = USER_INFO.get(principal);
        if(val != null && val.equals(password)){
            return new SimpleAuthenticationInfo(principal,password,getName());
        }
        return null;
    }
}
