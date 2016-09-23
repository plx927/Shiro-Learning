package com.panlingxiao.shiro.example2.dao;

import com.panlingxiao.shiro.example2.entity.Resource;

import java.util.List;


public interface ResourceDao extends BaseDao<Resource>{
	public List<Resource> listResource();
}
