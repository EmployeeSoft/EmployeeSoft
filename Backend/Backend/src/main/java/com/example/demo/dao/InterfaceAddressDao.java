package com.example.demo.dao;

import com.example.demo.entity.Address;
import com.example.demo.entity.Person;

import java.util.ArrayList;
import java.util.List;

public interface InterfaceAddressDao {
    // Given the Address information, get the Person who live at that address
    Person getPersonByAddress(Address address);

    // Given the person ID, get the address ID that belongs to that person
    int getAddressIdByPersonId(Integer personId);

    // Given the Address ID, get the address information
    Address getAddressById(Integer id);


    ///// REQUIRED METHODS BELOW /////


    // Get a list of address that is associated with the given person ID
    List<Address> getAddressListById(Integer id);

    // Given person ID, get the person's address line 1
    String getAddressLine1ByPersonId(Integer personId);

    // Given person ID, get the person's address line 2
    String getAddressLine2ByPersonId(Integer personId);

    // Given the person ID, get the city the person is living in
    String getCityByPersonId(Integer personId);

    // Given the person ID, get the zipcode of the city the person is living in
    String getZipcodeByPersonId(Integer personId);

    // Given the person ID, get the state name the person is residing
    String getStateNameByPersonId(Integer personId);

    // Given the person ID, get the state abbreviation the person is residing
    String getStateAbbrByPersonId(Integer personId);

    // Given the person ID, get the person's address information
    List<Address> getAddressListByPersonId(Integer personId);

}
