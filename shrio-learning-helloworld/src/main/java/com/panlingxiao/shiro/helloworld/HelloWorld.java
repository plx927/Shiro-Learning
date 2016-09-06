package com.panlingxiao.shiro.helloworld;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by panlingxiao on 2016/6/24.
 */
public class HelloWorld {

    private static Logger log = LoggerFactory.getLogger(HelloWorld.class);


    public static void main(String[] args) {

        //--------------构造Shiro的运行环境--------------------------

        /*
         * 从classpath中读取Ini配置文件，通过工厂创建Shrio的SecurityManger，
         * 默认情况下就会从classpath下的shiro的ini文件中读取
         */
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        //设置SecurityManager
        SecurityUtils.setSecurityManager(securityManager);


        // get the currently executing user:A Subject is just a security-specific "view" of an application User
        // getSubject()根据当前线程或者请求获取到用户数据信息，在所有的环境下都可以通过该方法获取当前的用户
        Subject currentUser = SecurityUtils.getSubject();


        // Do some stuff with a Session (no need for a web or EJB container!!!)
        /*
         * 这里的Session是由Shiro所提供的,它和我们的HttpSession类似，但是并需要Http环境
         */
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");

        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("Retrieved the correct value! [" + value + "]");
        }

        //----------------测试登入并且检查角色和权限---------------------

        //判断用户是否认证
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
                //输出用户信息
                log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
            } catch (UnknownAccountException uae) {
                log.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // 可以在这里捕获更多个用户自定义异常
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }

            //test a role:
            if (currentUser.hasRole("schwartz")) {
                log.info("May the Schwartz be with you!");
            } else {
                log.info("Hello, mere mortal.");
            }

            //test a typed permission (not instance-level)
            if (currentUser.isPermitted("lightsaber:abc")) {
                log.info("You may use a lightsaber ring.  Use it wisely.");
            } else {
                log.info("Sorry, lightsaber rings are for schwartz masters only.");
            }

            //a (very powerful) Instance Level permission:
            if (currentUser.isPermitted("user:get:5")) {
                log.info("You are permitted to 'get' the user with (id) '5'.  " +
                        "Here are the keys - have fun!");
            } else {
                log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
            }
            //all done - log out!
            currentUser.logout();



            System.exit(0);
        }
    }
}
