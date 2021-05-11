package dao.implementation;

import dao.InterfaceContactDao;
import entity.Contact;

public class ContactDao implements InterfaceContactDao {
    // Get the person's contact information from the person ID
    public Contact getContactByPersonId(Integer personId) {
        // TODO
        return new Contact();
    }

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
}
