package com.panlingxiao.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {

    @GetMapping("/login")
    public String login(String name,String pwd){
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(name,pwd));
        return "登入成功";
    }

    @GetMapping("/aa")
    public String aa(){
        return "请登入";
    }

    @GetMapping("/unauth")
    public String unauth(){
        return "你没有权限";
    }
}
