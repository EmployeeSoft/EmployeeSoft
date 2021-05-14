package com.example.demo.dao;

import com.example.demo.entity.PersonalDocument;

import java.sql.Date;
import java.util.List;

public interface InterfacePersonalDocDao {
    // Given the personal document ID, get the title of the document
    String getTitleById(Integer id);

    // Given the personal document ID, get the comment on that document
    String getCommentById(Integer id);

    // Given the personal document ID, get the date the document was created
    Date getDateCreatedById(Integer id);

    // Given the personal document ID, get the person who created the file
    String getCreatedByById(Integer id);


    ///// REQUIRED METHODS BELOW


    // Given the employee ID, get the Personal document information
    List<PersonalDocument> getPersonalDocumentListById(Integer employeeId);

    // Given the ID, get the personal document that is associated with that ID
    PersonalDocument getPersonalDocumentById(Integer id);

    // Given the employee ID, get the personal document ID
    int getPersonalDocIdByEmployeeId(Integer employeeId);

    // Given the personal document ID, get the employee ID
    int getEmployeeIdByPersonalDocId(Integer id);

    // Get the path to the file by using the personal document ID
    // Path to file will be the link to the SS3 buckets
    String getPathById(Integer id);

    // Create a new personal document to be stored in the database
    PersonalDocument createPersonalDocumentByEmployeeId(Integer employeeId, String path, String filename, String fileTitle);

    // Get the last ID in the personal table
    int getLastPersonalDocId();

    // Add form to database
    boolean addPersonalDoc(PersonalDocument document);
}
