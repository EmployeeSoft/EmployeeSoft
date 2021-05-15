package com.example.demo.dao;

import com.example.demo.entity.PersonalDocument;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface InterfacePersonalDocDao {
    // Create a new personal document to be stored in the database
    Object createPersonalDocumentByEmployeeId(Integer employeeId, String path, String filename, String fileTitle);

    // Get list of personal documents of an employee by using their employee ID
    ArrayList<PersonalDocument> getPersonalDocsByEmployeeId(Integer employeeId);

    // Check to see if the document is already in the database
    boolean checkIfExist(Integer employeeId, String path);
}
