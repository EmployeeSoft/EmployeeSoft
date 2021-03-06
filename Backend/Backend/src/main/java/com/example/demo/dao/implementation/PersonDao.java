package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfacePersonDao;
import com.example.demo.domain.*;
import com.example.demo.entity.Contact;
import com.example.demo.entity.Person;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Repository
public class PersonDao extends AbstractHibernateDao<Person> implements InterfacePersonDao {
    public PersonDao() { setClazz(Person.class); }

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

    // Get person's first name by user ID
    public String getFirstNameByUserId(Integer userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT firstName FROM Person WHERE userId = :userId");
        query.setParameter("userId", userId);
        String firstName = (String) query.uniqueResult();
        return firstName;
    }

    // Get person's middle name by user ID
    public String getMiddleNameByUserId(Integer userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT middleName FROM Person WHERE userId = :userId");
        query.setParameter("userId", userId);
        String middleName = (String) query.uniqueResult();
        return middleName;
    }

    // Get person's last name by user ID
    public String getLastNameByUserId(Integer userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT lastName FROM Person WHERE userId = :userId");
        query.setParameter("userId", userId);
        String lastName = (String) query.uniqueResult();
        return lastName;
    }

    // Get person's email by user ID
    public String getEmailByUserId(Integer userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT email FROM Person WHERE userId = :userId");
        query.setParameter("userId", userId);
        String email = (String) query.uniqueResult();
        return email;
    }

    // Get the person's cell phone number by user ID
    public String getCellPhoneByUserId(Integer userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT cellPhone FROM Person WHERE userId = :userId");
        query.setParameter("userId", userId);
        String cellPhone = (String) query.uniqueResult();
        return cellPhone;
    }

    // Get the person's alt phone number by user ID
    public String getAltPhoneByUserId(Integer userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT altPhone FROM Person WHERE userId = :userId");
        query.setParameter("userId", userId);
        String altPhone = (String) query.uniqueResult();
        return altPhone;
    }

    // Get the person's preferred name by user ID
    public String getPreferNameByUserId(Integer userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT preferName FROM Person WHERE userId = :userId");
        query.setParameter("userId", userId);
        String preferName = (String) query.uniqueResult();
        return preferName;
    }

    // Get the person's DoB by user ID
    public Date getDobByUserId(Integer userId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT dob FROM Person WHERE userId = :userId");
        query.setParameter("userId", userId);
        Date dob = (Date) query.uniqueResult();
        return dob;
    }

    // Update the person's Info by ID
    public boolean updatePersonInfo(PersonDomain personDomain) {
        try {
            Session session = getCurrentSession();
            Query query = session.createQuery("UPDATE Person SET preferName =: preferName, dob =: dob, gender =: gender, " +
                    "ssn =: ssn WHERE id = :id");
            query.setParameter("preferName", personDomain.getPreferName());
            query.setParameter("dob", Date.valueOf(personDomain.getDob()));
            query.setParameter("gender", personDomain.getGender());
            query.setParameter("ssn", personDomain.getSsn());
            query.setParameter("id", personDomain.getId());
            query.executeUpdate();
            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }
    @Override
    public Person addNewPerson(Person person) {
        return merge(person);
    }
  
    // Get the user ID from the person ID
    public int getUserIdByPersonId(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT userId FROM Person WHERE id = :personId");
        query.setParameter("personId", personId);
        return (int) query.uniqueResult();
    }
}
