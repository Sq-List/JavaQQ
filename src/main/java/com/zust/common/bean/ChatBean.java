package com.zust.common.bean;

import java.io.Serializable;

/**
 * Created by Zzzz on 2018/1/12.
 */
public class ChatBean implements Serializable
{
	private static final long serialVersionUID = 1L;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
