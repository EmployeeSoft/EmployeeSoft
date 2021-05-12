package com.example.demo.controller;

import com.example.demo.domain.UserDomain;
import com.example.demo.domain.common.ServiceStatus;
import com.example.demo.domain.response.LoginResponse;
import com.example.demo.service.LoginService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody UserDomain userDomain) {
//        LoginResponse loginResponse = new LoginResponse();
//        String username = userDomain.getUsername();
//        String pwd = userDomain.getPassword();
//
//        if (username == null || pwd == null || !loginService.validate(username, pwd)) {
//            loginResponse.setServiceStatus(new ServiceStatus("FAIL", false, "Wrong username or Password"));
//            return loginResponse;
//        }
//
//        String jwt = JwtUtil.generateToken()
//    }
}
