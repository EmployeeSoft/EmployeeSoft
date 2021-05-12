package com.example.demo.controller;

import com.example.demo.domain.EmployeeDomain;
import com.example.demo.domain.common.ServiceStatus;
import com.example.demo.domain.response.HomePageResponse;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/home")
    public HomePageResponse getHomePage() {
        HomePageResponse response = new HomePageResponse();
        response.setEmployeeDomain(employeeService.getEmployeeByEmployeeId(1));
        response.setServiceStatus(new ServiceStatus("SUCCESS", true, ""));
        return response;
    }
}
