package com.zust.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zzzz on 2018/1/12.
 */
public class SearchUserRequestBean implements Serializable
{

    private String info;

    private List<User> users;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
