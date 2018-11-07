package com.panlingxiao.shiro.authenticator.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

import java.util.List;


/**
 * 自己实现Realm，通常情况下，用户不会自己去直接实现Realm接口。
 * 而是去继承现成的Realm实现类，对于一个用户认证的Realm，可以去继承AuthenticatingRealm
 */
public class MyRealm1 implements Realm {

    @Override
    public String getName() {
        return "myrealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken; //仅支持UsernamePasswordToken类型的Token
    }


    /**
     * 根据一个指定的token,返回一个帐号指定的认证信息
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();  //得到用户名
        String password = new String((char[]) token.getCredentials()); //得到密码
        if (!"zhang".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
