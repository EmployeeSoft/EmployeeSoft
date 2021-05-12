package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/employee")
    public String getEmployee() {
        Employee employee = employeeService.getEmployeeByEmployeeId(1);
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        System.out.println(json);
        return json;
    }

    @ResponseBody
    @PostMapping("/manager")
    public int getManager() {
        int employee = employeeService.getManager(1);
        return employee;
    }
}
