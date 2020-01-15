package com.health.pojo;

import java.io.Serializable;

public class TSetmealCheckgroupKey implements Serializable{
    private Integer setmealId;

    private Integer checkgroupId;

    public Integer getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(Integer setmealId) {
        this.setmealId = setmealId;
    }

    public Integer getCheckgroupId() {
        return checkgroupId;
    }

    public void setCheckgroupId(Integer checkgroupId) {
        this.checkgroupId = checkgroupId;
    }
}