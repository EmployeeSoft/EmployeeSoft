package dao;

import entity.Contact;

public interface InterfaceContactDao {
    // Get the person's contact information from the person ID
    Contact getContactByPersonId(Integer personId);

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
}
