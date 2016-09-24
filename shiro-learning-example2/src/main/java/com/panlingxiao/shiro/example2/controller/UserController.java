package com.panlingxiao.shiro.example2.controller;

import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.entity.Role;
import com.panlingxiao.shiro.example2.entity.User;
import com.panlingxiao.shiro.example2.request.UserRequest;
import com.panlingxiao.shiro.example2.service.RoleService;
import com.panlingxiao.shiro.example2.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/admin/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 显示用户列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userService.list());
        return "user/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        UserRequest userRequest = new UserRequest();
        model.addAttribute("user", userRequest);
        //查询系统的所有角色
        model.addAttribute("roles", roleService.list());
        model.addAttribute("btnText", "添加用户");
        return "user/edit";
    }

    /**
     * 添加用户
     *
     * @param user
     * @param  roleIds
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(User user, @RequestParam("roleIds") List<Integer> roleIds, Model model) {
        //获取所有设置的角色
        //String[] trids = req.getParameterValues("roleIds");
        //List<Integer> rids = new ArrayList<>();
//		for(String rid:trids) {
//			rids.add(Integer.parseInt(rid));
//		}
        userService.add(user, roleIds);
        return "redirect:/admin/user/list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) throws InvocationTargetException, IllegalAccessException {
        User user = userService.load(id);
        UserRequest userRequest = new UserRequest();

        BeanUtils.copyProperties(userRequest,user);

        model.addAttribute("user", userRequest);
        model.addAttribute("roles", roleService.list());
        List<Role> hasRoles = userService.listRoleByUserId(id);
        List<Integer> rids = new ArrayList<>();
        for (Role r : hasRoles) {
            rids.add(r.getId());
        }
        userRequest.setRoleIds(rids);
        model.addAttribute("btnText", "更新用户");
        return "user/edit";
    }

    /**
     * 更新用户
     *
     * @param id
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id,@RequestParam("roleIds") List<Integer> roleIds, User user, Model model) {
        User tu = userService.load(id);
        tu.setNickname(user.getNickname());
        userService.update(tu, roleIds);
        return "redirect:/admin/user/list";
    }


    @RequestMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable int id) {
        User u = userService.load(id);
        if (u.getStatus() == 0) {
            u.setStatus(1);
        } else {
            u.setStatus(0);
        }
        userService.update(u);
        return "redirect:/admin/user/list";
    }


    @RequestMapping("/listRes/{id}")
    public String listRes(Model model, @PathVariable int id) {
        List<Resource> hasRes = userService.listAllResource(id);
        model.addAttribute("reses", hasRes);
        model.addAttribute("user", userService.load(id));
        return "user/res";
    }

}
