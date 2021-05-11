package com.example.demo.dao.implementation;

import com.example.demo.config.HibernateConfig;
import com.example.demo.dao.InterfacePersonDao;
import com.example.demo.entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.tree.ExpandVetoException;
import java.sql.Date;

public class PersonDao implements InterfacePersonDao {
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
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Person WHERE id = :id");
            query.setParameter("id", id);
            Person person = (Person) query.uniqueResult();
            transaction.commit();
            return person;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Given the person ID, get the person's first name
    public String getFirstNameById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT firstName FROM Person WHERE id = :id");
            query.setParameter("id", id);
            String firstName = (String) query.uniqueResult();
            transaction.commit();
            return firstName;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Given the person ID, get the person's last name
    public String getLastNameById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT lastName FROM Person WHERE id = :id");
            query.setParameter("id", id);
            String lastName = (String) query.uniqueResult();
            transaction.commit();
            return lastName;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Given the person ID, get the person's middle name
    public String getMiddleNameById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT middleName FROM Person WHERE id = :id");
            query.setParameter("id", id);
            String middleName = (String) query.uniqueResult();
            transaction.commit();
            return middleName;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Given the person ID, get the person's email
    public String getEmailById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT email FROM Person WHERE id = :id");
            query.setParameter("id", id);
            String email = (String) query.uniqueResult();
            transaction.commit();
            return email;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Given the person ID, get the person's cell phone number
    public String getCellPhoneById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT cellPhone FROM Person WHERE id = :id");
            query.setParameter("id", id);
            String cellPhone = (String) query.uniqueResult();
            transaction.commit();
            return cellPhone;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Given the person Id, get the person's Alt phone number
    public String getAltPhoneById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT altPhone FROM Person WHERE id = :id");
            query.setParameter("id", id);
            String altPhone = (String) query.uniqueResult();
            transaction.commit();
            return altPhone;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Given the person ID, get the gender as a string depending if it is a 1 or 0
    public String getGenderById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT gender FROM Person WHERE id = :id");
            query.setParameter("id", id);
            int gender = (int) query.uniqueResult();
            transaction.commit();
            return (gender == 1) ? "Female" : "Male";
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Get the last four digits of the SSN, by person ID
    public String getLastFourDigitSSNById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT ssn FROM Person WHERE id = :id");
            query.setParameter("id", id);
            String ssn = (String) query.uniqueResult();
            transaction.commit();
            return ssn.substring(5);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Get the person's Date of Birth by the person ID
    public Date getDobById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT dob FROM Person WHERE id = :id");
            query.setParameter("id", id);
            Date dob = (Date) query.uniqueResult();
            transaction.commit();
            return dob;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }
}
