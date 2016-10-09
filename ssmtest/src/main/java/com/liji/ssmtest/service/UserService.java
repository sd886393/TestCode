package com.liji.ssmtest.service;

import com.liji.ssmtest.model.User;

import java.util.List;

/**
 * Created by Jimmy on 2016/9/23.
 */
public interface UserService {
    User getUser(String userId);
    List<User> getAllUser();
    void addUser(User user);
}
