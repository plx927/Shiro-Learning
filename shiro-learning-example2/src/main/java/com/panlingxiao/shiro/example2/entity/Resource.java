package com.panlingxiao.shiro.example2.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by panlingxiao on 2016/9/23.
 */
@Entity
@Table(name="t_resource")
public class Resource {

    private Integer id;
    private String name;
    private String url;
    private String permission; //权限字符串
}
