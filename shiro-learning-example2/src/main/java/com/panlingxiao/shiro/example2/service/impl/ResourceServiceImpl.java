package com.panlingxiao.shiro.example2.service.impl;

import com.panlingxiao.shiro.example2.dao.ResourceDao;
import com.panlingxiao.shiro.example2.entity.Resource;
import com.panlingxiao.shiro.example2.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	public void add(Resource res) {
		resourceDao.add(res);
	}

	public void update(Resource res) {
		resourceDao.update(res);
	}

	public void delete(int id) {
		resourceDao.delete(id);
	}

	public Resource load(int id) {
		return resourceDao.load(id);
	}

	public List<Resource> listResource() {
		return resourceDao.listResource();
	}
}
