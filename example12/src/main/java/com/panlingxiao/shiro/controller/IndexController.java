package com.panlingxiao.shiro.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class IndexController {

    @RequestMapping("/hello")
    public String index(HttpSession httpSession){
        Subject subject = SecurityUtils.getSubject();
        // 往session里面去写两个数据，isAuthenticate:true
        // 写入身份信息:principal:user
        log.info("is authenticated:{}",subject.isAuthenticated());
        User user = (User) subject.getPrincipal();
        log.info("principal:{}", user);
        user.getName();
        return "shiro";
    }
}
