package com.panlingxiao.shiro.example2.service;

import com.panlingxiao.shiro.example2.entity.Resource;

import java.util.List;


public interface ResourceService  {
	public void add(Resource res);
	
	public void update(Resource res);
	
	public void delete(int id);
	
	public Resource load(int id);
	
	public List<Resource> listResource();
}
