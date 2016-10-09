package com.littleji.ssmtest.service;

import com.liji.ssmtest.model.User;
import com.liji.ssmtest.service.UserService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Jimmy on 2016/9/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/User-servlet.xml")
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void getUserService(){
        User user = userService.getUser("1");
        Assert.assertNotNull(user);
    }

}

