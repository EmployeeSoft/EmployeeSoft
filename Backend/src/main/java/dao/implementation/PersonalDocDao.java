package dao.implementation;

import dao.InterfacePersonalDocDao;
import entity.PersonalDocument;

import java.sql.Date;

public class PersonalDocDao implements InterfacePersonalDocDao {
    // Given the employee ID, get the personal document ID
    public int getPersonalDocIdByEmployeeId(Integer employeeId) {
        // TODO
        return 0;
    }

    // Given the personal document ID, get the employee ID
    public int getEmployeeIdByPersonalDocId(Integer id) {
        // TODO
        return 0;
    }

    // Get the path to the file by using the personal document ID
    // Path to file will be the link to the SS3 buckets
    public String getPathById(Integer id) {
        // TODO
        return "";
    }

    // Given the personal document ID, get the title of the document
    public String getTitleById(Integer id) {
        // TODO
        return "";
    }

    // Given the personal document ID, get the comment on that document
    public String getCommentById(Integer id) {
        // TODO
        return "";
    }

    // Given the personal document ID, get the date the document was created
    public Date getDateCreatedById(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the personal document ID, get the person who created the file
    public String getCreatedByById(Integer id) {
        // TODO
        return "";
    }

    // Given the ID, get the Personal document information
    public PersonalDocument getPersonalDocumentById(Integer id) {
        // TODO
        return new PersonalDocument();
    }
}
