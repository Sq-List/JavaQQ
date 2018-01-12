package com.zust.common.bean;

import java.util.List;

/**
 * Created by Zzzz on 2018/1/12.
 */
public class LoginBean {

    private int type;

    private User loginUser;

    private List<User> friends;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
