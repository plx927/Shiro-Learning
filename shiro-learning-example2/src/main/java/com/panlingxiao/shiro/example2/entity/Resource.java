package com.panlingxiao.shiro.example2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by panlingxiao on 2016/9/23.
 */
@Entity
@Table(name="t_resource")
public class Resource {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String url; //资源对应的url
    private String permission; //权限字符串

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
