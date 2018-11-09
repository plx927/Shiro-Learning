package com.panlingxiao.shiro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationTemp;

@SpringBootApplication
@Slf4j
public class ShiroApp {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApp.class,args);
        log.info("session dir:{}", new ApplicationTemp().getDir("servlet-sessions"));
    }


}
