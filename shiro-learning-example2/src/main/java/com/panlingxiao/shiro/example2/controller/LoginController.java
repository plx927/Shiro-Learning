package com.panlingxiao.shiro.example2.controller;

import com.panlingxiao.shiro.example2.util.ShiroKit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		String emsg = null;
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			emsg = e.getMessage();
		}
		if(ShiroKit.isEmpty(emsg)) {
			return "redirect:/";
		} else {
			model.addAttribute("emsg", emsg);
			return "login";
		}
	}
}