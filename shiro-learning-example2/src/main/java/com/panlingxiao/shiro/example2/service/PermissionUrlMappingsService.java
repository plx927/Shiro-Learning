package com.panlingxiao.shiro.example2.service;

import com.panlingxiao.shiro.example2.dao.ResourceDao;
import com.panlingxiao.shiro.example2.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by panlingxiao on 2016/9/23.
 */
@Component("permissionUrlMappingsService")
public class PermissionUrlMappingsService {

    private Map<String, String> permissionUrlMap;

    @Autowired
    private ResourceDao resourceDao;

    public String getResourceByUrl(String url) {
        if (permissionUrlMap == null) {
            List<Resource> resources = resourceDao.listResource();
            permissionUrlMap = new HashMap<String,String>();
            for (Resource resource : resources) {
                permissionUrlMap.put(resource.getUrl(), resource.getPermission());
            }
        }
        return permissionUrlMap.get(url);
    }


}
