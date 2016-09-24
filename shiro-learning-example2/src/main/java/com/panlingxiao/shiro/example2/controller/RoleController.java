package com.panlingxiao.shiro.example2.controller;

import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.entity.Role;
import com.panlingxiao.shiro.example2.request.RoleRequest;
import com.panlingxiao.shiro.example2.service.ResourceService;
import com.panlingxiao.shiro.example2.service.RoleService;
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

@Controller
@RequestMapping("/admin/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("roles", roleService.list());
		return "role/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("role", new RoleRequest());
		//查询所有资源
		List<Resource> resources = resourceService.listResource();
		model.addAttribute("resources",resources);
		model.addAttribute("btnText", "添加角色");
		return "role/edit";
	}

	/**
	 * 添加角色
	 * @param role
	 * @param resIds
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Role role,@RequestParam(name = "resIds",required = false)List<Integer> resIds) {
		roleService.add(role, resIds);
		return "redirect:/admin/role/list";
	}


	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(Model model,@PathVariable int id) throws InvocationTargetException, IllegalAccessException {
		Role role = roleService.load(id);
		RoleRequest roleRequest = new RoleRequest();
		BeanUtils.copyProperties(roleRequest,role);
		model.addAttribute("role", roleRequest);
		//查询所有资源
		List<Resource> resources = resourceService.listResource();
		model.addAttribute("resources",resources);

		//查询该角色所具备的资源
		List<Resource> hasRes = roleService.listRoleResource(id);
		List<Integer> hasResIds = new ArrayList<>();
		for(Resource resource : hasRes){
			hasResIds.add(resource.getId());
		}
		model.addAttribute("hasResIds",hasResIds);
		model.addAttribute("btnText","更新角色");
		return "role/edit";
	}

	/**
	 * 更新角色
	 * @param id
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String add(@PathVariable int id,Role role,@RequestParam(name = "resIds",required = false)List<Integer> resIds,Model model) {
		Role tr = roleService.load(id);
		tr.setName(role.getName());
		tr.setDescription(role.getDescription());
		roleService.update(tr, resIds);
		return "redirect:/admin/role/list";
	}

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id){
		roleService.delete(id);
		return "redirect:/admin/role/list";
	}


//	/**
//	 * 显示角色设置权限的页面
//	 * @param model
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/setRes/{id}")
//	public String listRes(Model model,@PathVariable int id) {
//		List<Resource> hasRes = roleService.listRoleResource(id);
//		List<Integer> hrIds = new ArrayList<>();
//		//当前用户具备的资源ID
//		for(Resource r:hasRes) {
//			hrIds.add(r.getId());
//		}
//		model.addAttribute("rids", hrIds);
//		//查询所有的资源
//		model.addAttribute("reses", resourceService.listResource());
//		model.addAttribute("role", roleService.load(id));
//		return "role/res";
//	}
//
//	/**
//	 * 为具体的角色设置权限
//	 * @param resId
//	 * @param roleId
//	 * @param c
//	 * @param resp
//	 * @throws IOException
//	 */
//	@RequestMapping(value="/setRes",method=RequestMethod.POST)
//	public void setRes(int resId,int roleId,int c,HttpServletResponse resp) throws IOException {
//		if(c==0) {
//			roleService.deleteRoleResource(roleId, resId);
//		} else {
//			roleService.addRoleResource(roleId, resId);
//		}
//		resp.getWriter().println("ok");
//	}


	
}
