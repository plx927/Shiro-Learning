package com.panlingxiao.shiro.session.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * Created by panlingxiao on 2016/9/22.
 */
public class MySessionListener extends SessionListenerAdapter{


    @Override
    public void onStart(Session session) {
        System.out.println("Session开启:"+session);
    }

    @Override
    public void onStop(Session session) {
        System.out.println("Session销毁:"+session);
    }

}
