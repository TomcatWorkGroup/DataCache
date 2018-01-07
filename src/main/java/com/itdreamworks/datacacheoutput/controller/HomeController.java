package com.itdreamworks.datacacheoutput.controller;

import com.itdreamworks.datacacheoutput.config.DeviceFocusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    DeviceFocusConfig config;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String Index(){
        DeviceFocusConfig o = config;
        return "Hello";
    }
}
