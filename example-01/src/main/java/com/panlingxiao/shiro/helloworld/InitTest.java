package com.panlingxiao.shiro.helloworld;

import org.apache.shiro.config.Ini;

import java.util.Map;

/**
 * Created by panlingxiao on 2016/9/5.
 * 测试 Shiro 所编写的Ini
 */
public class InitTest {

    public static void main(String[] args) {
        Ini ini = new Ini();
        ini.loadFromPath("classpath:shiro.ini");
        System.out.println(ini);
        System.out.println("---------------------------------");

        // print users section
        Ini.Section userSection = ini.getSection("users");
        System.out.println(Ini.SECTION_PREFIX + userSection + Ini.SECTION_SUFFIX);
        for (Map.Entry<String, String> entry : userSection.entrySet()) {
            System.out.println(entry);
        }

        // print roles section
        Ini.Section rolesSection = ini.getSection("roles");
        System.out.println(Ini.SECTION_PREFIX + rolesSection + Ini.SECTION_SUFFIX);
        for (Map.Entry<String, String> entry : rolesSection.entrySet()) {
            System.out.println(entry);
        }
    }
}
