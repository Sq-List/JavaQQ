package com.zust.common.bean;

import java.io.Serializable;

/**
 * Created by Zzzz on 2018/1/12.
 */
public class AddFriendRequestBean implements Serializable
{
	private static final long serialVersionUID = 1L;

    private int type;

    private User user;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
