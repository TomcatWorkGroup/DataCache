package com.itdreamworks.datacacheoutput.controller;

import com.itdreamworks.datacacheoutput.service.EmployeeService;
import com.itdreamworks.datacacheoutput.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
public class LoginController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    TokenService tokenService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletResponse response, @RequestParam(name = "loginid") String loginId, @RequestParam(name = "password") String password){
        String pass = employeeService.getPassword(loginId);
        String result ;
        if(password.equals(pass)){
            Cookie tokenCookie = tokenService.getUserToken(loginId);
            result = "{\"code\":1,\"msg\":\"用户登录成功！\"}";
            response.addCookie(tokenCookie);
        }else{
            result = "{\"code\":0,\"msg\":\"用户名或密码错误！\"}";
        }
        return result;
    }
}
