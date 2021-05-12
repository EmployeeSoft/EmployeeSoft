package com.example.demo.controller;

import com.example.demo.domain.EmployeeDomain;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseBody
    @RequestMapping("/employee")
    public String getEmployee() {
        EmployeeDomain employee = employeeService.getEmployeeByEmployeeId(1);
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        System.out.println(json);
        return json;
    }

    @ResponseBody
    @PostMapping("/manager")
    public int getManager() {
        int employee = employeeService.getManagerIdByEmployeeId(1);
        return employee;
    }
}
