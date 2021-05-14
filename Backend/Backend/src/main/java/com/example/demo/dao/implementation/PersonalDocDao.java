package com.example.demo.dao.implementation;

import com.example.demo.config.HibernateConfig;
import com.example.demo.dao.InterfacePersonalDocDao;
import com.example.demo.domain.EmployeeDomain;
import com.example.demo.entity.Employee;
import com.example.demo.entity.PersonalDocument;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PersonalDocDao extends AbstractHibernateDao<PersonalDocument> implements InterfacePersonalDocDao {
    public PersonalDocDao() { setClazz(PersonalDocument.class); }

    @Autowired
    private EmployeeDao employeeDao;

    // Create a new personal document to be stored in the database
    public PersonalDocument createPersonalDocumentByEmployeeId(Integer employeeId, String path, String filename,
                                                               String fileTitle) {
        // Create an employee
        // Used in the creation of the personal document
        Employee employee = employeeDao.getEmployeeByEmployeeId(employeeId);

        // Get the current date in java.sql.Date
        long millis = System.currentTimeMillis();
        Date currentDate = new Date(millis);

        // Create a document to return
        PersonalDocument document = new PersonalDocument();
        document.setEmployeeId(employeeId);
        document.setEmployee(employee);
        document.setPath(path);
        document.setTitle(fileTitle);
        document.setComment("");
        document.setDateCreated(currentDate);
        document.setCreatedBy(employeeDao.getFirstNameByEmployeeId(employeeId));

        // Add to database
        Session session = getCurrentSession();

        try {
            session.save(document);
            System.out.println("Successfully added " + document.getTitle() + " to database");
        } catch (Exception e) {
            System.out.println("An error had occurred: " + e);
        }

        return document;
    }
}
