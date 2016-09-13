package com.panlingxiao.shiro.configuration.program;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.*;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panlingxiao on 2016/9/13.
 * 编程式配置
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
