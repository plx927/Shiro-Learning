package com.panlingxiao.shiro.filter;

import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by panlingxiao on 2016/9/19.
 * 自定义过滤器，将其添加到Shiro的过滤器链中
 */
public class MyOncePerRequestFilter extends OncePerRequestFilter{

    private static final Logger log = LoggerFactory.getLogger(MyOncePerRequestFilter.class);

    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("MyOncePerRequestFilter filter request");
        chain.doFilter(request,response);
    }
}
