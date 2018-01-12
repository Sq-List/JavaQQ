package com.zust.common.bean;

import java.io.Serializable;

/**
 * Created by Zzzz on 2018/1/12.
 */
public class UserStateBean implements Serializable
{

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
