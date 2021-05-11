package com.example.demo.dao;

import com.example.demo.entity.VisaStatus;

import java.sql.Date;

public interface InterfaceVisaStatusDao {
    // Get the visa status ID, by type
    int getIdByType(String visaType);

    // Get the visa type by ID
    String getVisaTypeByID(Integer id);

    // Given the visa status ID, notify user if it is active
    boolean getIsActiveById(Integer id);

    // Given the visa status ID, get the date last modified
    Date getDateModifiedById(Integer id);


    ///// REQUIRED METHODS BELOW /////

    // Given the ID, get the visa status information
    VisaStatus getVisaStatusById(Integer id);

    // Visa Status Management is only available if the user is NOT a citizen or a green card holder
    Boolean visaStatusManagementAble(String createUser);

    // Given the visa status ID, get the create user ?
    String getCreateUserById(Integer id);
}
