package com.github.zhangkaitao.shiro.chapter16.web.controller;

import com.github.zhangkaitao.shiro.chapter16.entity.Resource;
import com.github.zhangkaitao.shiro.chapter16.entity.User;
import com.github.zhangkaitao.shiro.chapter16.service.ResourceService;
import com.github.zhangkaitao.shiro.chapter16.service.UserService;
import com.github.zhangkaitao.shiro.chapter16.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;


@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;


    /**
     * 查询菜单在前台界面显示
     *
     * @param loginUser
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(@CurrentUser User loginUser, Model model) {
        //查询用户具备的权限
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        //查询用户可以访问的资源
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
