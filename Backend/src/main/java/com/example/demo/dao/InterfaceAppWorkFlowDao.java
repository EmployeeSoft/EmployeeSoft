package com.example.demo.dao;


import com.example.demo.entity.ApplicationWorkFlow;

import java.util.Date;

public interface InterfaceAppWorkFlowDao {
    // Given the employee ID, get the ID of that employee Application WorkFlow
    int getIdByEmployeeId(Integer employeeId);

    // Given the Application WorkFlow ID, get the Employee ID that it belongs to
    int getEmployeeIdById(Integer id);

    // Given the Application WorkFlow ID, get the date it was created
    Date getDateCreatedById(Integer id);

    // Given the Application WorkFlow ID, get the date it was modified
    Date getDateModifiedById(Integer id);

    // Given the Application WorkFlow ID, get the status of the application
    boolean getStatusById(Integer id);

    // Given the Application WorkFlow Id, get the comment for that ID
    String getCommentByID(Integer id);

    // Given the employee ID, get the comment that was intended for that employee from the HR
    String getCommentByEmployeeId(Integer employeeId);

    // Given the Application WorkFlow ID, get the application type
    String getTypeById(Integer id);

    // Given the Application WorkFlow ID, get the application's information
    ApplicationWorkFlow getApplicationWorkFlowById(Integer id);
}
