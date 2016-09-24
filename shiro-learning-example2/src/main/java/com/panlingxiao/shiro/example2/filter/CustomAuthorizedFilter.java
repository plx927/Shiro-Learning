package com.panlingxiao.shiro.example2.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by panlingxiao on 2016/9/23.
 * 自定义认证过滤器,根据用户请求的URL
 */
public class CustomAuthorizedFilter extends PathMatchingFilter {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthorizedFilter.class);

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String[] array = (String[]) mappedValue;
        log.debug("配置的参数为:{}", Arrays.asList(array));

        //需要将URL查询对应的Permission
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = WebUtils.getPathWithinApplication(httpRequest);
        if(log.isDebugEnabled()) {
            log.debug("请求的URI:{}", requestURI);
        }
        Subject subject = SecurityUtils.getSubject();
        return subject.isPermitted(requestURI);
    }

}
