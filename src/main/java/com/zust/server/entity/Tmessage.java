package com.zust.server.entity;

import java.util.Date;

public class Tmessage {
    private Integer id;

    private Integer sendId;

    private Integer reciveId;

    private Date time;

    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public Integer getReciveId() {
        return reciveId;
    }

    public void setReciveId(Integer reciveId) {
        this.reciveId = reciveId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}