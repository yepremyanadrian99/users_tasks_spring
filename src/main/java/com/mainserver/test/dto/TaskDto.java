package com.mainserver.test.dto;

import com.mainserver.test.entity.User;

import java.util.Date;

public class TaskDto {
    private String name;
    private Date created;
    private User user;

    public TaskDto() {}

    public TaskDto(String name, Date created, User user) {
        this.name = name;
        this.created = created;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
