package com.example.demo.dao;

import com.example.demo.entity.Employee;

import java.io.File;
import java.sql.Date;

public interface InterfaceEmployeeDao {
    // Given the person ID, get their employee ID
    int getEmployeeIdByPersonId(Integer id);

    // Given the employee ID, get that employee's person ID
    int getPersonIdByEmployeeId(Integer id);

    // Given the employee ID, get the title
    String getTitleByEmployeeId(Integer id);

    // Given the employee ID, get the start date of that employee
    Date getStartDateByEmployeeId(Integer id);

    // Given the employee ID, get the end date of the employee
    Date getEndDateByEmployeeId(Integer id);

    // Get the employee's avatar pic from the database using the employee ID
    File getAvatarByEmployeeId(Integer id);

    // Given the employee ID, get the employee's car information
    String getCarIntoByEmployeeId(Integer id);

    // Given the employee ID, get the visa status ID
    int getVisaStatusIdByEmployeeId(Integer id);

    // Given the employee ID, get the visa start date
    Date getVisaStartDateByEmployeeId(Integer id);

    // Given the employee ID, get the visa end date
    Date getVisaEndDateByEmployeeId(Integer id);

    // Get the employee's driver license number by using the employee ID
    String getDriverLicenseByEmployeeId(Integer id);

    // Given the employee ID, get the employee's driver license expire date
    Date getDriverLicenseExpDateByEmployeeId(Integer id);

    // Given the driver license number, get the license exp date
    Date getDriverLicenseExpDateByLicenseNumber(String driverLicenseNum);



    ///// REQUIRED METHODS BELOW /////


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
}
