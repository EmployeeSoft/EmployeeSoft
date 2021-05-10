package dao.implementation;

import dao.InterfaceAddressDao;
import entity.Address;
import entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao implements InterfaceAddressDao {
    // Given person ID, get the person's address line 1
    public String getAddressLine1ByPersonId(Integer personId) {
        // TODO
        return "";
    }

    // Given person ID, get the person's address line 2
    public String getAddressLine2ByPersonId(Integer personId) {
        // TODO
        return "";
    }

    // Given the person ID, get the city the person is living in
    public String getCityByPersonId(Integer personId) {
        // TODO
        return "";
    }

    // Given the person ID, get the zipcode of the city the person is living in
    public String getZipcodeByPersonId(Integer personId) {
        // TODO
        return "";
    }

    // Given the person ID, get the state name the person is residing
    public String getStateNameByPersonId(Integer personId) {
        // TODO
        return "";
    }

    // Given the person ID, get the state abbreviation the person is residing
    public String getStateAbbrByPersonId(Integer personId) {
        // TODO
        return "";
    }

    // Given the person ID, get the person's address information
    public Address getAddressByPersonId(Integer personId) {
        // TODO
        return new Address();
    }

    // Given the Address information, get the Person who live at that address
    public Person getPersonByAddress(Address address) {
        // TODO
        return new Person();
    }

    // Given the person ID, get the address ID that belongs to that person
    public int getAddressIdByPersonId(Integer personId) {
        // TODO
        return 0;
    }

    // Given the Address ID, get the address information
    public Address getAddressById(Integer id) {
        // TODO
        return new Address();
    }
}
