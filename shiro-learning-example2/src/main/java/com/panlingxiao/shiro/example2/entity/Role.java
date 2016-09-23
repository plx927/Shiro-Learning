package com.panlingxiao.shiro.example2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by panlingxiao on 2016/9/23.
 */
@Entity
@Table(name="t_role")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;




}
