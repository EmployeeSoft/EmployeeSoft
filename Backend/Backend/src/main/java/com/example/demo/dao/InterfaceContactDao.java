package com.example.demo.dao;

import com.example.demo.entity.Contact;

import java.util.List;

public interface InterfaceContactDao {
    // Given the contact ID, get the person ID who that contact belongs to
    int getPersonIdByContactId(Integer id);

    // Given the person ID, get the contact ID for that person
    int getContactIdByPersonId(Integer personId);

    // Given the person ID, get the relationship of that contact for that person ID
    String getRelationshipByPersonId(Integer personId);

    // Given the person ID, get the title of that contact for that person ID
    String getTitleByPersonId(Integer personId);

    // Given the contact ID, get if the contact is reference
    boolean getIsReferenceById(Integer contactId);

    // Given the contact ID, get if the contact is an emergency contact
    boolean getIsEmergencyById(Integer contactId);

    // Given the contact ID, get if the contact is landlord
    boolean getIsLandlordById(Integer contactId);

    // Get the person's contact information from the contact ID
    Contact getContactByContactId(Integer contactId);



    ///// REQUIRED METHODS BELOW /////


    // Find the contact who recommended the employee and return the requirements
    List<Contact> getContactByReference(Integer personId);

    // There can be many contacts for one person. Given the person ID, get all of their contacts
    List<Contact> getContactListByPersonId(Integer personId);
}
