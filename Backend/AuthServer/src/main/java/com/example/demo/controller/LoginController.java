package com.example.demo.controller;

import com.example.demo.constant.JwtConstant;
import com.example.demo.domain.UserDomain;
import com.example.demo.domain.common.ServiceStatus;
import com.example.demo.domain.response.LoginResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import com.example.demo.security.util.CookieUtil;
import com.example.demo.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public LoginResponse login(@RequestBody UserDomain userDomain, HttpServletResponse response) {
        LoginResponse loginResponse = new LoginResponse();
        String username = userDomain.getUsername();
        String password = userDomain.getPassword();
        User user = null;

        if (username == null || password == null || (user = loginService.validate(username, password)) == null) {
            loginResponse.setServiceStatus(new ServiceStatus("FAIL", false, "Wrong username or Password"));
            return loginResponse;
        }
        Role role = loginService.getRoleByUser(user);
        String jwt = JwtUtil.generateToken(username, JwtConstant.JWT_VALID_DURATION);
        CookieUtil.create(response, JwtConstant.JWT_COOKIE_NAME, jwt, false, -1, "localhost");
        loginResponse.setServiceStatus(new ServiceStatus("SUCCESS", true, ""));
        loginResponse.setRedirectUrl("http://localhost:4200/home?id=" + user.getId());
        loginResponse.setRole(role.getRoleName());
        return loginResponse;
    }

    @GetMapping("/logout")
    public LoginResponse logout(HttpServletResponse response) {
        LoginResponse loginResponse = new LoginResponse();
        CookieUtil.clear(response, JwtConstant.JWT_COOKIE_NAME, "localhost");
        loginResponse.setServiceStatus(new ServiceStatus("SUCCESS", true, ""));
        loginResponse.setRedirectUrl("http://localhost:4200/login");
        return loginResponse;
    }
}
