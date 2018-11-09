package com.panlingxiao.shiro.controller;

import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;


import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    public IniRealm iniRealm(){
        Ini ini = Ini.fromResourcePath("classpath:shiro.ini");
       return new IniRealm(ini);
    }
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(new MyRealm());
        return defaultWebSecurityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Autowired SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/aa");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");

        ApiAuthenticationFilter apiAuthenticationFilter = new ApiAuthenticationFilter();
        // 设置定义Filter
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put(apiAuthenticationFilter.getName(), apiAuthenticationFilter);
        shiroFilterFactoryBean.setFilters(filterMap);


        // 设置哪些URL需要进行拦截处理
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login", DefaultFilter.anon.name());
        filterChainDefinitionMap.put("/unauth", DefaultFilter.anon.name());
        filterChainDefinitionMap.put("/**",apiAuthenticationFilter.getName());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * Shiro集成Spring使用的Filter
     */
    @Bean
    FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxyFilter() {
        FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(Integer.MAX_VALUE - 1);
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        filterRegistrationBean.addInitParameter("targetFilterLifecycle", "true");
        return filterRegistrationBean;
    }
}
