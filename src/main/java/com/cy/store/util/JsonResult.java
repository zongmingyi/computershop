package com.cy.store.util;

import java.io.Serializable;

/**
 * Json格式的数据进行响应
 */
public class JsonResult<E> implements Serializable {
    /**
     * 状态码
     */
    private Integer state;
    /**  描述信息*/
    private String message;
    /** 数据 因为不确定什么类型的数据，所以用泛型*/
    private E data;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    //不同的构造函数为了满足不同的需求
    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    //出现异常
    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
}
