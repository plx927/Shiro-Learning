package com.panlingxiao.shiro.example2.controller;

import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.entity.Role;
import com.panlingxiao.shiro.example2.entity.User;
import com.panlingxiao.shiro.example2.service.RoleService;
import com.panlingxiao.shiro.example2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/admin/user")
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", userService.list());
		return "user/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleService.list());
		return "user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(User user,HttpServletRequest req,Model model) {
		String[] trids = req.getParameterValues("rids");
		List<Integer> rids = new ArrayList<Integer>();
		for(String rid:trids) {
			rids.add(Integer.parseInt(rid));
		}
		userService.add(user, rids);
		return "redirect:/admin/user/list";
	}
	
	@RequestMapping("/updateStatus/{id}")
	public String updateStatus(@PathVariable int id) {
		User u = userService.load(id);
		if(u.getStatus()==0) {
			u.setStatus(1);
		} else {
			u.setStatus(0);
		}
		userService.update(u);
		return "redirect:/admin/user/list";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		User user = userService.load(id);
		model.addAttribute("user", user);
		model.addAttribute("roles", roleService.list());
		List<Role> hasRoles = userService.listRoleByUserId(id);
		List<Integer> rids = new ArrayList<Integer>();
		for(Role r:hasRoles) {
			rids.add(r.getId());
		}
		model.addAttribute("hasRole", rids);
		return "user/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,HttpServletRequest req,User user,Model model) {
		User tu = userService.load(id);
		tu.setNickname(user.getNickname());
		String[] trids = req.getParameterValues("rids");
		List<Integer> rids = new ArrayList<Integer>();
		for(String rid:trids) {
			rids.add(Integer.parseInt(rid));
		}
		userService.update(tu, rids);
		return "redirect:/admin/user/list";
	}
	
	@RequestMapping("/listRes/{id}")
	public String listRes(Model model,@PathVariable int id) {
		List<Resource> hasRes = userService.listAllResource(id);
		model.addAttribute("reses", hasRes);
		model.addAttribute("user", userService.load(id));
		return "user/res";
	}
}
