package com.example.demo.dao;


import com.example.demo.entity.ApplicationWorkFlow;

import java.sql.Date;
import java.util.ArrayList;

public interface InterfaceAppWorkFlowDao {
    // Given the Application WorkFlow ID, get the application's information
    ApplicationWorkFlow getApplicationWorkFlowById(Integer id);

    // Get a list of all application work flow by employee id
    ArrayList<ApplicationWorkFlow> getAppWorkFlowsByEmployeeId(Integer employeeId);

    // Get the application workflow by user ID
    ApplicationWorkFlow getCurrentByUserId(Integer userId);

    // Check to see if the employee has started an application
    boolean checkEmployeeAppWorkFlowExist(Integer userId);

    // Get the current active workflow and change that to false
    void updateCurrentAppWorkFlow(Integer userId);

    // First step in the process
    Object createAppWorkFlow(Integer userId, String filename);

    // Get the comment
    String getComment(Integer userId);

    // Get the type
    String getType(Integer userId);

    // Get the date created
    Date getDateCreatedByUserId(Integer userId);

    // Get the date modified
    Date getDateModifiedByUserId(Integer userId);
}
