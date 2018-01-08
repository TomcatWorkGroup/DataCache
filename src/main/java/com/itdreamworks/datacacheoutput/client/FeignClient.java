package com.itdreamworks.datacacheoutput.client;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.cloud.netflix.feign.FeignClient(name = "baidu",url = "${romteurl}")
public interface FeignClient {
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String getString();
}

