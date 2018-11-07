package com.panlingxiao.shiro.authenticaion;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by panlingxiao on 2016/9/6.
 * 实现一个认证的Realm，这里的realm的name是我们在INI配置的Name，其原因是
 * AuthenticatingRealm的继承了CachingRealm,而CachingRealm实现了Nameable接口，
 * 因此它会将Realm的名字注入进来。
 */
public class MyAuthenticatedRealm extends AuthenticatingRealm {


    private static final Map<Object, Object> USER_INFO = new HashMap<Object, Object>();

    static {
        USER_INFO.put("zs", "123");
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token instanceof UsernamePasswordToken) {
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
            //获取用户的身份
            String principal = (String) usernamePasswordToken.getPrincipal();
            String credential = new String((char[]) usernamePasswordToken.getCredentials());
            if (credential.equals(USER_INFO.get(principal))) {
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal + "@163.com", credential, getName());
                //获取到当前Realm所产生的Principal
                System.out.println("available principal is : " + getAvailablePrincipal(authenticationInfo.getPrincipals()));
                return authenticationInfo;
            }
        }
        return null;
    }
}
