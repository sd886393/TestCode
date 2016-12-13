package com.littleji.web.controller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jimmy on 2016/12/12.
 */
@Controller
@EnableAutoConfiguration
public class HelloControler {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
