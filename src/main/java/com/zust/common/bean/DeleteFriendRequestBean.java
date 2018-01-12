package com.zust.common.bean;

/**
 * Created by Zzzz on 2018/1/12.
 */
public class DeleteFriendRequestBean {

    private User asker;

    private User bedeleteder;

    public User getAsker() {
        return asker;
    }

    public void setAsker(User asker) {
        this.asker = asker;
    }

    public User getBedeleteder() {
        return bedeleteder;
    }

    public void setBedeleteder(User bedeleteder) {
        this.bedeleteder = bedeleteder;
    }
}
