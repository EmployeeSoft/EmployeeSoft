package com.example.demo.dao;

import com.example.demo.domain.ContactDomain;
import com.example.demo.domain.PersonalContactDomain;
import com.example.demo.entity.Contact;

import java.util.List;

public interface InterfaceContactDao {
    // Find the contact who recommended the employee and return the requirements
    List<Contact> getContactByReference(Integer personId);

    // There can be many contacts for one person. Given the person ID, get all of their contacts
    List<Contact> getContactListByPersonId(Integer personId);

    void addNewContact(Contact contact);

    boolean updateContact(ContactDomain domain);
}
