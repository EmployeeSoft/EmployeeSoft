package dao;

import entity.PersonalDocument;

import java.sql.Date;

public interface InterfacePersonalDocDao {
    // Given the employee ID, get the personal document ID
    int getPersonalDocIdByEmployeeId(Integer employeeId);

    // Given the personal document ID, get the employee ID
    int getEmployeeIdByPersonalDocId(Integer id);

    // Get the path to the file by using the personal document ID
    // Path to file will be the link to the SS3 buckets
    String getPathById(Integer id);

    // Given the personal document ID, get the title of the document
    String getTitleById(Integer id);

    // Given the personal document ID, get the comment on that document
    String getCommentById(Integer id);

    // Given the personal document ID, get the date the document was created
    Date getDateCreatedById(Integer id);

    // Given the personal document ID, get the person who created the file
    String getCreatedByById(Integer id);

    // Given the ID, get the Personal document information
    PersonalDocument getPersonalDocumentById(Integer id);
}
