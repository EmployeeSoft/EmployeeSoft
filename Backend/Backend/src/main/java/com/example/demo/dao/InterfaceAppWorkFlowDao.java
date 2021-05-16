package com.example.demo.dao;


import com.example.demo.entity.ApplicationWorkFlow;

import java.util.ArrayList;
import java.util.Date;

public interface InterfaceAppWorkFlowDao {
    // Given the Application WorkFlow ID, get the application's information
    ApplicationWorkFlow getApplicationWorkFlowById(Integer id);

    // Get a list of all application work flow by employee id
    ArrayList<ApplicationWorkFlow> getAppWorkFlowsByEmployeeId(Integer employeeId);
}
