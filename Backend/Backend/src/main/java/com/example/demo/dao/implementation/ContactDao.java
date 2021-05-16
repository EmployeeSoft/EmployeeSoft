package com.example.demo.dao.implementation;

import com.example.demo.config.HibernateConfig;
import com.example.demo.dao.InterfaceContactDao;
import com.example.demo.domain.ContactDomain;
import com.example.demo.domain.PersonalContactDomain;
import com.example.demo.entity.Contact;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class ContactDao extends AbstractHibernateDao<Contact> implements InterfaceContactDao {
    public ContactDao() { setClazz(Contact.class); }

    // There can be many contacts for one person. Given the person ID, get all of their contacts
    public List<Contact> getContactListByPersonId(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Contact WHERE personId = :personId");
        query.setParameter("personId", personId);
        List<Contact> contacts = query.list();
        return contacts;
    }

    @Override
    public void addNewContact(Contact contact) {
        merge(contact);
    }

    // Find the contact who recommended the employee and return the requirements
    public List<Contact> getContactByReference(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Contact WHERE isReference = true AND personId = :personId");
        query.setParameter("personId", personId);
        List<Contact> referencedContact = query.list();
        return referencedContact;
    }

    // Update Contact with person ID
    public boolean updateContact(ContactDomain domain){
        Session session = getCurrentSession();
        Query query = session.createQuery("UPDATE Contact SET fullName =: fullName," +
                " phone =: phone, relationship =: relationship, title =: title, " +
                " address =: address WHERE personId =: id");
        query.setParameter("fullName", domain.getFullName());
        query.setParameter("phone", domain.getPhone());
        query.setParameter("relationship", domain.getRelationship());
        query.setParameter("title", domain.getTitle());
        query.setParameter("address", domain.getAddress());
        query.setParameter("id", domain.getId());
        int res = query.executeUpdate();
        return res != 0;
    }
}
