package com.liji.ssmtest.controller;

import com.liji.ssmtest.model.User;
import com.liji.ssmtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jimmy on 2016/9/21.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/showUser", method = RequestMethod.GET)
    public String showUser(@RequestParam("id") String id, ModelMap modelMap){
        User user = userService.getUser(id);
        modelMap.put("user", user);
        return "showUser";
    }

    @RequestMapping(value = "/allUser", method = RequestMethod.GET)
    public String allUser( ModelMap modelMap){
        List<User> user = userService.getAllUser();
        modelMap.put("user", user);
        return "allUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(@RequestParam("name")String name, @RequestParam("age")String age, ModelMap modelMap){
        User user = new User();
        user.setUser_name(name);
        user.setUser_age(age);
        userService.addUser(user);
        return "redirect:allUser";
    }

    @RequestMapping(value="/getUserWithJson", method = RequestMethod.GET)
    public @ResponseBody User getUserWithJson(@RequestParam("id")String id){
        return userService.getUser(id);
    }


    /* 不使用模板
    public void showUser(HttpServletResponse response) throws IOException{
        response.getWriter().print("<h1>Hello</h1>");
        response.flushBuffer();
    }
    */

}
