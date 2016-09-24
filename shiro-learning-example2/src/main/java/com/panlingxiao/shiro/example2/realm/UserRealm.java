package com.panlingxiao.shiro.example2.realm;

import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.entity.User;
import com.panlingxiao.shiro.example2.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UserRealm extends AuthorizingRealm {


    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {

        User user = ((User) principals.getPrimaryPrincipal());
        int uid = user.getId();

        if (log.isDebugEnabled()) {
            log.debug("用户{}请求授权操作", user.getUsername());
        }
        //查询用户角色
        List<String> roles = userService.listRoleNameByUser(uid);

        //查询用户可以访问的资源
        List<Resource> reses = userService.listAllResource(uid);

        List<String> permissions = new ArrayList<>();

        for (Resource r : reses) {
            permissions.add(r.getUrl());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(new HashSet<>(roles));
        info.setStringPermissions(new HashSet<>(permissions));
        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        String password = new String((char[]) token.getCredentials());
        User user = userService.login(username, password);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getUsername()));
        log.info("用户{}认证成功", token.getPrincipal());
        return info;
    }


//
//	@Override
//	protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
//		//System.out.println("清除授权的缓存");
//		Cache c = this.getAuthorizationCache();
//		Set<Object> keys = c.keys();
//		for(Object o:keys) {
//			System.out.println("授权缓存:"+o+"-----"+c.get(o)+"----------");
//		}
//
//		super.clearCachedAuthorizationInfo(principals);
//	}
//
//	@Override
//	protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
//		//System.out.println("清除认证的缓存");
//		Cache c = this.getAuthenticationCache();
//		Set<Object> keys = c.keys();
//		for(Object o:keys) {
//			System.out.println("认证缓存:"+o+"----------"+c.get(o)+"----------");
//		}
//		User user = ((User)principals.getPrimaryPrincipal());
//		SimplePrincipalCollection spc = new SimplePrincipalCollection(user.getUsername(), getName());
//		super.clearCachedAuthenticationInfo(spc);
//	}
//


}
