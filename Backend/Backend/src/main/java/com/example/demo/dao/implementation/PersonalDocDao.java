package com.example.demo.dao.implementation;

import com.example.demo.config.HibernateConfig;
import com.example.demo.dao.InterfacePersonalDocDao;
import com.example.demo.entity.PersonalDocument;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PersonalDocDao extends AbstractHibernateDao<PersonalDocument> implements InterfacePersonalDocDao {
    public PersonalDocDao() { setClazz(PersonalDocument.class); }

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


    ///// REQUIRED METHODS BELOW /////


    // Given the employee ID, get the Personal documents information
    public List<PersonalDocument> getPersonalDocumentListById(Integer employeeId) {
        // TODO
        // Will implement once we get SS3 buckets running

        return new LinkedList<PersonalDocument>();
    }

    // Given the ID, get the personal document that is associated with that ID
    public PersonalDocument getPersonalDocumentById(Integer id) {
        // TODO
        // Will implement once we get SS3 buckets running

        return new PersonalDocument();
    }

    // Given the employee ID, get the personal document ID
    public int getPersonalDocIdByEmployeeId(Integer employeeId) {
        // TODO
        // Will implement once we get SS3 buckets running

        return 0;
    }

    // Given the personal document ID, get the employee ID
    public int getEmployeeIdByPersonalDocId(Integer id) {
        // TODO
        // Will implement once we get SS3 buckets running
        return 0;
    }

    // Get the path to the file by using the personal document ID
    // Path to file will be the link to the SS3 buckets
    public String getPathById(Integer id) {
        // TODO
        // Will implement once we get SS3 buckets running
        return "";
    }
}
