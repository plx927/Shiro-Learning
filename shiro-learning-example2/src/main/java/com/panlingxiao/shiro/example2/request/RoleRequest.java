package com.panlingxiao.shiro.example2.request;

import java.util.List;

/**
 * Created by panlingxiao on 2016/9/24.
 */
public class RoleRequest {
    private Integer id;
    private String name;
    private String description;
    private List<Integer> resIds;

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



    public List<Integer> getResIds() {
        return resIds;
    }

    public void setResIds(List<Integer> resIds) {
        this.resIds = resIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
