package com.health.pojo;

import java.io.Serializable;

public class TCheckgroupCheckitemKey implements Serializable{
    private Integer checkgroupId;

    private Integer checkitemId;

    public Integer getCheckgroupId() {
        return checkgroupId;
    }

    public void setCheckgroupId(Integer checkgroupId) {
        this.checkgroupId = checkgroupId;
    }

    public Integer getCheckitemId() {
        return checkitemId;
    }

    public void setCheckitemId(Integer checkitemId) {
        this.checkitemId = checkitemId;
    }
}