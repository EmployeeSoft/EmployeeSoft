package com.example.demo.dao.implementation;

import com.example.demo.config.HibernateConfig;
import com.example.demo.dao.InterfaceAddressDao;
import com.example.demo.entity.Address;
import com.example.demo.entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDao implements InterfaceAddressDao {
    // Given the Address information, get the Person who live at that address
    public Person getPersonByAddress(Address address) {
        // TODO
        return new Person();
    }

    // Given the person ID, get the address ID that belongs to that person
    public int getAddressIdByPersonId(Integer personId) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT id FROM Address WHERE personId = :personId");
            query.setParameter("personId", personId);
            int id = (int) query.uniqueResult();
            transaction.commit();
            return id;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }

        return -1;
    }

    // Given the Address ID, get the address information
    public Address getAddressById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Address WHERE id = :id");
            query.setParameter("id", id);
            Address address = (Address) query.uniqueResult();
            transaction.commit();
            return address;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    public List<Address> getAddressList() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Address");
            List<Address> addressList = query.list();
            transaction.commit();
            System.out.println("Got Address List");
            return addressList;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }


    ///// REQUIRED METHODS BELOW /////


    // Given person ID, get the person's address line 1
    public String getAddressLine1ByPersonId(Integer personId) {
        Transaction transaction = null;
        try {
            // Get current session
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();

            // Get the address line 1 in the database where person ID = personId
            Query query = session.createQuery("SELECT addressLine1 FROM Address WHERE personId = :personId");
            query.setParameter("personId", personId);

            // Save the result
            String addressLine1 = (String) query.uniqueResult();
            transaction.commit();

            // Output for confirmation
            System.out.println("Address line 1 obtained");
            return addressLine1;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }

        // Return null if error. Should be handle in Service Layer using Optional Class
        return null;
    }

    // Given person ID, get the person's address line 2
    public String getAddressLine2ByPersonId(Integer personId) {
        Transaction transaction = null;
        try {
            // Get current session
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();

            // Get the address line 1 in the database where person ID = personId
            Query query = session.createQuery("SELECT addressLine2 FROM Address WHERE personId = :personId");
            query.setParameter("personId", personId);

            // Save the result
            String addressLine2 = (String) query.uniqueResult();
            transaction.commit();

            // Output for confirmation
            System.out.println("Address Line 2 obtained");
            return addressLine2;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }

        // Return null if error. Should be handle in Service Layer using Optional Class
        return null;
    }

    // Given the person ID, get the city the person is living in
    public String getCityByPersonId(Integer personId) {
        Transaction transaction = null;
        try {
            // Get current session
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();

            // Get the address line 1 in the database where person ID = personId
            Query query = session.createQuery("SELECT city FROM Address WHERE personId = :personId");
            query.setParameter("personId", personId);

            // Save the result
            String city = (String) query.uniqueResult();
            transaction.commit();

            // Output for confirmation
            System.out.println("City name obtained");
            return city;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }

        // Return null if error. Should be handle in Service Layer using Optional Class
        return null;
    }

    // Given the person ID, get the zipcode of the city the person is living in
    public String getZipcodeByPersonId(Integer personId) {
        Transaction transaction = null;
        try {
            // Get current session
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();

            // Get the address line 1 in the database where person ID = personId
            Query query = session.createQuery("SELECT zipcode FROM Address WHERE personId = :personId");
            query.setParameter("personId", personId);

            // Save the result
            String zipcode = (String) query.uniqueResult();
            transaction.commit();

            // Output for confirmation
            System.out.println("Zipcode obtained");
            return zipcode;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }

        // Return null if error. Should be handle in Service Layer using Optional Class
        return null;
    }

    // Given the person ID, get the state name the person is residing
    public String getStateNameByPersonId(Integer personId) {
        Transaction transaction = null;
        try {
            // Get current session
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();

            // Get the address line 1 in the database where person ID = personId
            Query query = session.createQuery("SELECT stateName FROM Address WHERE personId = :personId");
            query.setParameter("personId", personId);

            // Save the result
            String stateName = (String) query.uniqueResult();
            transaction.commit();

            // Output for confirmation
            System.out.println("State name obtained");
            return stateName;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }

        // Return null if error. Should be handle in Service Layer using Optional Class
        return null;
    }

    // Given the person ID, get the state abbreviation the person is residing
    public String getStateAbbrByPersonId(Integer personId) {
        Transaction transaction = null;
        try {
            // Get current session
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();

            // Get the address line 1 in the database where person ID = personId
            Query query = session.createQuery("SELECT stateAbbr FROM Address WHERE personId = :personId");
            query.setParameter("personId", personId);

            // Save the result
            String stateAbbr = (String) query.uniqueResult();
            transaction.commit();

            // Output for confirmation
            System.out.println("State abbreviation obtained");
            return stateAbbr;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }

        // Return null if error. Should be handle in Service Layer using Optional Class
        return null;
    }

    // Given the person ID, get the person's address information
    public List<Address> getAddressListByPersonId(Integer personId) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Address WHERE personId = :personId");
            query.setParameter("personId", personId);
            List<Address> addressList = query.list();
            transaction.commit();
            return addressList;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Get a list of address that is associated with the given person ID
    public List<Address> getAddressListById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Address WHERE id = :id");
            query.setParameter("id", id);
            List<Address> addressList = query.list();
            transaction.commit();
            return addressList;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }
}
