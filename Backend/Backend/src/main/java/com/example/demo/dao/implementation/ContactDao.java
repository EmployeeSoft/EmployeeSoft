package com.example.demo.dao.implementation;

import com.example.demo.config.HibernateConfig;
import com.example.demo.dao.InterfaceContactDao;
import com.example.demo.entity.Contact;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class ContactDao implements InterfaceContactDao {
    // Given the contact ID, get the person ID who that contact belongs to
    public int getPersonIdByContactId(Integer id) {
        // TODO
        return 0;
    }

    // Given the person ID, get the contact ID for that person
    public int getContactIdByPersonId(Integer personId) {
        // TODO
        return 0;
    }

    // Given the person ID, get the relationship of that contact for that person ID
    public String getRelationshipByPersonId(Integer personId) {
        // TODO
        return "";
    }

    // Given the person ID, get the title of that contact for that person ID
    public String getTitleByPersonId(Integer personId) {
        // TODO
        return "";
    }

    // Given the contact ID, get if the contact is reference
    public boolean getIsReferenceById(Integer contactId) {
        // TODO
        return false;
    }

    // Given the contact ID, get if the contact is an emergency contact
    public boolean getIsEmergencyById(Integer contactId) {
        // TODO
        return false;
    }

    // Given the contact ID, get if the contact is landlord
    public boolean getIsLandlordById(Integer contactId) {
        // TODO
        return false;
    }

    // Get the person's contact information from the contact ID
    public Contact getContactByContactId(Integer contactId) {
        // TODO
        return new Contact();
    }


    ///// REQUIRED METHODS BELOW /////


    // There can be many contacts for one person. Given the person ID, get all of their contacts
    public List<Contact> getContactListByPersonId(Integer personId) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Contact WHERE personId = :personId");
            query.setParameter("personId", personId);
            List<Contact> contacts = query.list();
            transaction.commit();
            return contacts;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }

        return null;
    }

    // Find the contact who recommended the employee and return the requirements
    public List<Contact> getContactByReference(Integer personId) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Contact WHERE isReference = true AND personId = :personId");
            query.setParameter("personId", personId);
            List<Contact> referencedContact = query.list();
            transaction.commit();
            return referencedContact;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }
}
