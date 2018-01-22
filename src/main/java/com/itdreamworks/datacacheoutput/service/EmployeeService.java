package com.itdreamworks.datacacheoutput.service;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    public String[] getEmployeeDevicesId(String employId) {
        return null;
    }

    public String getPassword(String loginId) {
        return loginId.equals("admin") ? "123456" : null;
    }
}
