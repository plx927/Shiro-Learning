package com.panlingxiao.shiro.helloworld;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panlingxiao on 2016/9/13.
 * 编程式配置
 *
 * 使用编程式配置的存在的缺点:
 * 1.它要求用户必须知道一个接口的具体实现，并且直接实例化一个具体的实现类。但是我们使用接口的一个很重要的目的
 *   就是为了向上层用户屏蔽具体的实现，提供统一的API。
 *
 * 2.由于Java语言本身的特性，当需要为SecurityManager中的组件设置属性时，必须先通过get方法获取到组件，
 *  然后将其进行强制的类型转换，这种写法是丑陋、冗余、并且将代码与实现类进行了紧耦合。
 *
 * 3.
 *
 */
public class ProgramConfiguration {

    public static void main(String[] args) {
        List<Realm> realms = new ArrayList<Realm>();
        //TODO 设置Realm
        org.apache.shiro.mgt.SecurityManager securityManager = new DefaultSecurityManager(realms);
        SecurityUtils.setSecurityManager(securityManager);

        //以上3步就完成了Shiro环境的配置




    }
}
