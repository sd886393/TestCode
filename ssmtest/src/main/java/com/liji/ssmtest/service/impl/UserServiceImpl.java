package com.liji.ssmtest.service.impl;

import com.liji.ssmtest.mapper.UserMapper;
import com.liji.ssmtest.model.User;
import com.liji.ssmtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Jimmy on 2016/9/23.
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    public User getUser(String userId) {
        return this.userMapper.getUser(userId);
    }
}
