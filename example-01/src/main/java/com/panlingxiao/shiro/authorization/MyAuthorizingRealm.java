package com.panlingxiao.shiro.authorization;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by panlingxiao on 2016/9/13.
 * 自定义一个授权的Realm,授权的Realm同时实现了认证功能
 *
 * 一旦继承AuthorizingRealm，那么该Realm就实现了Authorizer接口
 * 又因为AuthorizingRealm继承了AuthenticatingRealm，那么它也具备了认证功能。
 *
 */
public class MyAuthorizingRealm extends AuthorizingRealm {

    static final Map<String, UserInfo> USER_INFO_MAP = new HashMap<String, UserInfo>();

    static {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("hello");
        userInfo.setPassword("123");
        userInfo.setRoles(Arrays.asList("admin", "user"));
        userInfo.setPermissions(Arrays.asList("user:*"));
        USER_INFO_MAP.put("hello", userInfo);
    }

    static class UserInfo {
        private String username;
        private String password;
        private List<String> roles;
        private List<String> permissions;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        public List<String> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<String> permissions) {
            this.permissions = permissions;
        }
    }

    /**
     * 获取认证信息
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        String credentials = new String((char[]) token.getCredentials());
        UserInfo userInfo = USER_INFO_MAP.get(principal);
        AuthenticationInfo authenticationInfo = null;
        if (credentials != null && userInfo != null) {
            authenticationInfo = credentials.equals(userInfo.getPassword()) ? new SimpleAuthenticationInfo(principal, credentials, getName()) : null;
        }
        return authenticationInfo;
    }


    /**
     * 获取授权信息
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String principal = (String) principals.getPrimaryPrincipal();
        UserInfo userInfo = USER_INFO_MAP.get(principal);
        if(userInfo != null){
            //通过UserInfo，将角色信息和授权信息添加到授权信息对象中去，然后让Shiro来判断这个授权信息中是否包含指定了角色和权限
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo.addRoles(userInfo.getRoles());
            authorizationInfo.addStringPermissions(userInfo.getPermissions());
            return authorizationInfo;
        }
        return null;
    }


}
