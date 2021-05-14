package com.example.demo.dao;

import com.example.demo.entity.PersonalDocument;

import java.sql.Date;
import java.util.List;

public interface InterfacePersonalDocDao {
    // Create a new personal document to be stored in the database
    PersonalDocument createPersonalDocumentByEmployeeId(Integer employeeId, String path, String filename, String fileTitle);
}
