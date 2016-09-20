package com.panlingxiao.shiro.filter;

import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by panlingxiao on 2016/9/20.
 * 自定义AOP风格的Filter
 */
public class MyAdviceFilter extends AdviceFilter{

    private static final Logger log = LoggerFactory.getLogger(MyAdviceFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        log.info("MyAdviceFilter preHandle");
        //return super.preHandle(request, response);

        /*
         * Shiro会对请求的URI做进一步的封装处理
         * 默认情况下,Servlet容器不会对请求进行的URI进行编解码
         * 如果请求/shiro-web/test/a.jsp;jsessionId=xxx
         * 使用原生的HttpServletRequest获取到requestURI返回的包含;jsessionId=xxx,
         * 使用Shiro则会将这部分信息去除
         */
        String requestURI = WebUtils.getRequestUri((HttpServletRequest) request);
        String requestURI2 = ((HttpServletRequest) request).getRequestURI();
        System.out.println("requestURI:"+requestURI);
        System.out.println("requestURI2:"+requestURI2);
        if(requestURI.lastIndexOf("/test/a.jsp") != -1){
            return false;
        }
        return true;
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        log.info("MyAdviceFilter postHandle");
    }

    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        log.info("MyAdviceFilter afterCompletion");
    }
}
