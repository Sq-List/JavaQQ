package com.zust.common.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Zzzz on 2018/1/12.
 */
public class LoginBean implements Serializable
{

    private int type;

    private User loginUser;

    private Map<Integer, User> friendMap;

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

	public Map<Integer, User> getFriendMap()
	{
		return friendMap;
	}

	public void setFriendMap(Map<Integer, User> friendMap)
	{
		this.friendMap = friendMap;
	}
}
