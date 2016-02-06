package com.rhg.mvp.bean;

import java.util.List;

public class DataStream {
    private List<Group> group;
    private List<List<User>> user;

    public List<Group> getGroup() {
        return group;
    }

    public void setGroup(List<Group> group) {
        this.group = group;
    }

    public List<List<User>> getUser() {
        return user;
    }

    public void setUser(List<List<User>> user) {
        this.user = user;
    }

}
