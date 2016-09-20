package com.panlingxiao.shiro.filter;

import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;

/**
 * Created by panlingxiao on 2016/9/20.
 */
public class MyPathMatchingFilter extends PathMatchingFilter{

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        System.out.println("url matches,config is " + Arrays.toString((String[]) mappedValue));
        return super.onPreHandle(request, response, mappedValue);
    }
}
