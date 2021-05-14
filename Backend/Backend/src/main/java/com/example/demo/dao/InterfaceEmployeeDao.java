package com.example.demo.dao;

import com.example.demo.entity.Employee;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

public interface InterfaceEmployeeDao {
    // Given the employee ID, get the manager ID
    int getManagerIdByEmployeeId(Integer id);

    // Given the employeeId, get the employee information
    Employee getEmployeeByEmployeeId(Integer id);

    // Using the employee personId, determine if they hold a green card or a US citizen
    boolean isStatusManagementAble(Integer personId);

    // Given the person ID, get the employee avatar url link
    String getAvatarLink(Integer personId);

    // Given the person ID, get the Visa Type as a string
    String getVisaTypeByPersonId(Integer personId);

    // Given the person ID, get the visa start date
    Date getVisaStartDateByPersonId(Integer personId);

    // Given the person ID, get the visa end date
    Date getVisaEndDateByPersonId(Integer personId);

    // Given the person ID, get the employee start date
    Date getEmployeeStartDateByPersonId(Integer personId);

    // Given the person ID, get the employee end date
    Date getEmployeeEndDateByPersonId(Integer personId);

    // Get the employee title by person ID
    String getTitleByPersonId(Integer personId);

    // Get the employee car
    String getCarByPersonId(Integer personId);

    // Get the employee information from the person ID
    Employee getEmployeeByPersonId(Integer personId);

    // Given the personId, merge the file
    void updateEmployeeAvatarByPersonId(Integer personId, String avatar);

    // Given the person ID, get their employee ID
    int getEmployeeIdByPersonId(Integer personId);

    // Get the employee's name
    String getFirstNameByEmployeeId(Integer id);

    // Get all employees for HR
    ArrayList<Employee> getAllEmployees();
}
