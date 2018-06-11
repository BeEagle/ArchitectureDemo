package com.troy.androidrc.model;

/**
 * Created by Troy on 2018/6/9.
 */

public class User {

    public String userId;

    public String name;

    public String phone;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
