package com.liji.ssmtest.model;

/**
 * Created by Jimmy on 2016/9/23.
 */

public class User {
    private String user_name;
    private String user_age;

    public User(int id, String name, String age){
        super();
        this.setUser_id(id);
        this.setUser_name(name);
        this.setUser_age(age);

    }
    public User(){
        super();


    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private int user_id;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_age() {
        return user_age;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }
}
