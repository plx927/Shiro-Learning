package com.panlingxiao.shiro.example2.filter;

import com.panlingxiao.shiro.example2.service.PermissionUrlMappingsService;
import com.panlingxiao.shiro.example2.util.SpringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by panlingxiao on 2016/9/23.
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
        //通过requestURI请求对应的权限字符串
        PermissionUrlMappingsService permissionUrlMappingsService = SpringUtil.getBean("permissionUrlMappingsService");
        String permission = permissionUrlMappingsService.getResourceByUrl(requestURI);
        if(permission == null) {
            return super.onPreHandle(request, response, mappedValue);
        }else{
            Subject subject = SecurityUtils.getSubject();
            if(subject.isPermitted(permission)){
                return true;
            }else{
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.sendRedirect(httpRequest.getServletContext().getContextPath()+"/unauthorized.jsp");
                return false;
            }
        }
    }
}
