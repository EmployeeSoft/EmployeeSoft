package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfacePersonalDocDao;
import com.example.demo.entity.Employee;
import com.example.demo.entity.PersonalDocument;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;

@Repository
public class PersonalDocDao extends AbstractHibernateDao<PersonalDocument> implements InterfacePersonalDocDao {
    public PersonalDocDao() { setClazz(PersonalDocument.class); }

    @Autowired
    private EmployeeDao employeeDao;

    // Create a new personal document to be stored in the database
    public Object createPersonalDocumentByEmployeeId(Integer employeeId, String path, String filename,
                                                               String fileTitle) {
        if (!checkIfExist(employeeId, path)) {
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
                return session.merge(document);
            } catch (Exception e) {
                System.out.println("An exception as been thrown: " + e);
                e.printStackTrace();
            }
        }

        return null;
    }

    // Get list of personal documents of an employee by using their employee ID
    public ArrayList<PersonalDocument> getPersonalDocsByEmployeeId(Integer employeeId) {
        ArrayList<PersonalDocument> documents = new ArrayList<>();
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM PersonalDocument WHERE employeeId = :employeeId");
        query.setParameter("employeeId", employeeId);
        return (ArrayList<PersonalDocument>) query.list();
    }

    // Check to see if the document is already in the database
    public boolean checkIfExist(Integer employeeId, String path) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM PersonalDocument WHERE employeeId = :employeeId AND path = :path");
        query.setParameter("employeeId", employeeId);
        query.setParameter("path", path);
        return query.list().size() >= 1;
    }

    // Method to get the path from S3 bucket
    // We will use this method to get the path (which is the key in the S3 bucket) to download that file
    public String getPath(Integer userId, String fileTitle) {
        Session session = getCurrentSession();
        String hql = "SELECT doc.path " +
                     "FROM PersonalDocument doc, Employee e, Person p " +
                     "WHERE p.userId = :userId AND e.personId = p.id AND e.id = doc.employeeId AND doc.title = :fileTitle";
        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        query.setParameter("fileTitle", fileTitle);
        return (String) query.uniqueResult();
    }
}
