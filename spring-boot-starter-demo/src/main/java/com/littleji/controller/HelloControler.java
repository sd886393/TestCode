package com.littleji.controller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Jimmy on 2016/12/12.
 */
@Controller
@EnableAutoConfiguration
public class HelloControler {

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


    public static void main(String[] args) {
        SpringApplication.run(HelloControler.class, args);
    }
}
