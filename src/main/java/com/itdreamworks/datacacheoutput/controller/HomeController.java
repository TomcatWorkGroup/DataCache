package com.itdreamworks.datacacheoutput.controller;

import com.itdreamworks.datacacheoutput.client.FeignClient;
import com.itdreamworks.datacacheoutput.config.DeviceFocusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    //@Autowired
    //DeviceFocusConfig config;
    //@Autowired
    //FeignClient client;
    @Value("${romteurl}")
    String value;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String Index(){
        //DeviceFocusConfig o = config;
        //String s = client.getString();
        return value;
    }
}
