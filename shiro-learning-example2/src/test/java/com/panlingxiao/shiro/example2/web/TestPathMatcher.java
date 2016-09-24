package com.panlingxiao.shiro.example2.web;

import org.apache.shiro.util.AntPathMatcher;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by panlingxiao on 2016/9/23.
 */
public class TestPathMatcher {

    @Test
    public void testAntPathMatcher(){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        Assert.assertTrue(antPathMatcher.match("/admin/*", "/admin/list"));
        Assert.assertFalse(antPathMatcher.match("/admin/*", "/admin/list/abc"));
        Assert.assertTrue(antPathMatcher.match("/admin/**", "/admin/list"));
        Assert.assertTrue(antPathMatcher.match("/admin/**","/admin/list/abc"));
        Assert.assertTrue(antPathMatcher.match("/admin/**","/admin/list/abc/def"));
        org.junit.Assert.assertTrue(antPathMatcher.match("/user/get/*","/user/get/1"));
        org.junit.Assert.assertFalse(antPathMatcher.match("/user/get/*","/user/get"));
        org.junit.Assert.assertFalse(antPathMatcher.match("/user/get*","/user/get/1"));
    }
}
