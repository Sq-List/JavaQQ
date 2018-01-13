package com.zust.server.entity;

public class Tfriend {
    private Integer id;

    private Integer requestUserId;

    private Integer berequestUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequestUserId() {
        return requestUserId;
    }

    public void setRequestUserId(Integer requestUserId) {
        this.requestUserId = requestUserId;
    }

    public Integer getBerequestUserId() {
        return berequestUserId;
    }

    public void setBerequestUserId(Integer berequestUserId) {
        this.berequestUserId = berequestUserId;
    }
}