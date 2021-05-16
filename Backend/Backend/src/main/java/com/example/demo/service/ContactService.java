package com.example.demo.service;

import com.example.demo.dao.InterfaceContactDao;
import com.example.demo.domain.ContactDomain;
import com.example.demo.domain.PersonalContactDomain;
import com.example.demo.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    private InterfaceContactDao contactDao;

    @Autowired
    public void setContactDao(InterfaceContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Transactional
    public ArrayList<ContactDomain> getContactByReference(Integer personId) {
        List<Contact> contacts = contactDao.getContactByReference(personId);
        ArrayList<ContactDomain> contactDomains = new ArrayList<>();

        for (Contact contact : contacts) {
            ContactDomain domain = ContactDomain.builder()
                    .fullName(contact.getFullName())
                    .phone(contact.getPhone())
                    .relationship(contact.getRelationship())
                    .address(contact.getAddress())
                    .build();
            contactDomains.add(domain);
        }

        return contactDomains;
    }

    @Transactional
    public ArrayList<ContactDomain> getContactListByPersonId(Integer personId) {
        List<Contact> contacts = contactDao.getContactListByPersonId(personId);
        ArrayList<ContactDomain> contactDomains = new ArrayList<>();

        for (Contact contact : contacts) {
            ContactDomain domain = ContactDomain.builder()
                    .id(contact.getId())
                    .fullName(contact.getFullName())
                    .phone(contact.getPhone())
                    .relationship(contact.getRelationship())
                    .address(contact.getAddress())
                    .build();
            contactDomains.add(domain);
        }

        return contactDomains;
    }

    @Transactional
    public boolean updateContactByPersonId(ContactDomain[] domains){
        for(ContactDomain domain : domains){
            if(!contactDao.updateContact(domain)){
                return false;
            }
        }
        return true;
    }
}
