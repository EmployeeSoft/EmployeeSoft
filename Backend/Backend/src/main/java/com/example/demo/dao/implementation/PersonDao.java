package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfacePersonDao;
import com.example.demo.entity.Person;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class PersonDao extends AbstractHibernateDao<Person> implements InterfacePersonDao {
    public PersonDao() { setClazz(Person.class); }

    // Given the person's SSN, get the person's information
    public Person getPersonBySSN(String ssn) {
        // TODO
        return new Person();
    }

    // Given the person's first name, last name, and date of birth, get the person information
    public Person getPersonByInfo(String firstName, String lastName, Date dob) {
        // TODO
        return new Person();
    }

    // Get the person's SSN, by the person ID
    public String getSSNById(Integer id) {
        // TODO
        return "";
    }

    // Given the person ID, get the user ID
    public int getUserIdById(Integer id) {
        // TODO
        return 0;
    }


    ///// REQUIRED METHODS BELOW /////


    // Given the ID, get the person's information
    public Person getPersonById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Person WHERE id = :id");
        query.setParameter("id", id);
        Person person = (Person) query.uniqueResult();
        return person;
    }

    // Given the person ID, get the person's first name
    public String getFirstNameById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT firstName FROM Person WHERE id = :id");
        query.setParameter("id", id);
        String firstName = (String) query.uniqueResult();
        return firstName;
    }

    // Given the person ID, get the person's last name
    public String getLastNameById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT lastName FROM Person WHERE id = :id");
        query.setParameter("id", id);
        String lastName = (String) query.uniqueResult();
        return lastName;
    }

    // Given the person ID, get the person's middle name
    public String getMiddleNameById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT middleName FROM Person WHERE id = :id");
        query.setParameter("id", id);
        String middleName = (String) query.uniqueResult();
        return middleName;
    }

    // Given the person ID, get the person's email
    public String getEmailById(Integer id) {
            Session session = getCurrentSession();
            Query query = session.createQuery("SELECT email FROM Person WHERE id = :id");
            query.setParameter("id", id);
            String email = (String) query.uniqueResult();
            return email;
    }

    // Given the person ID, get the person's cell phone number
    public String getCellPhoneById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT cellPhone FROM Person WHERE id = :id");
        query.setParameter("id", id);
        String cellPhone = (String) query.uniqueResult();
        return cellPhone;
    }

    // Given the person Id, get the person's Alt phone number
    public String getAltPhoneById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT altPhone FROM Person WHERE id = :id");
        query.setParameter("id", id);
        String altPhone = (String) query.uniqueResult();
        return altPhone;
    }

    // Given the person ID, get the gender as a string depending if it is a 1 or 0
    public String getGenderById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT gender FROM Person WHERE id = :id");
        query.setParameter("id", id);
        int gender = (int) query.uniqueResult();
        return (gender == 1) ? "Female" : "Male";
    }

    // Get the last four digits of the SSN, by person ID
    public String getLastFourDigitSSNById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT ssn FROM Person WHERE id = :id");
        query.setParameter("id", id);
        String ssn = (String) query.uniqueResult();
        return ssn.substring(5);
    }

    // Get the person's Date of Birth by the person ID
    public Date getDobById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT dob FROM Person WHERE id = :id");
        query.setParameter("id", id);
        Date dob = (Date) query.uniqueResult();
        return dob;
    }

    // Get the person's information from the user ID
    public Person getPersonByUserId(Integer userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Person WHERE userId = :userId");
        query.setParameter("userId", userId);
        Person person = (Person) query.uniqueResult();
        return person;
    }

    // Given the user ID, get the person's gender and return either Male or Female
    public String getGenderByUserId(Integer userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT gender FROM Person WHERE userId = :userId");
        query.setParameter("userId", userId);
        int gender = (int) query.uniqueResult();
        return (gender == 1) ? "Female" : "Male";
    }

    // Get the last four digits of the SSN, by user ID
    public String getLastFourDigitSSNByUserId(Integer userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT ssn FROM Person WHERE userId = :userId");
        query.setParameter("userId", userId);
        String ssn = (String) query.uniqueResult();
        return ssn.substring(5);
    }

    // Given the user ID get the person ID
    public int getPersonIdByUserId(Integer userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT id FROM Person WHERE userId = :userId");
        query.setParameter("userId", userId);
        int id = (int) query.uniqueResult();
        return id;
    }
}
