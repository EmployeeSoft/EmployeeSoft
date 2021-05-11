package com.example.demo.dao;

import com.example.demo.entity.Person;

import java.sql.Date;

public interface InterfacePersonDao {
    // Given the ID, get the person's information
    Person getPersonById(Integer id);

    // Given the person's SSN, get the person's information
    Person getPersonBySSN(String ssn);

    // Given the person's first name, last name, and date of birth, get the person information
    Person getPersonByInfo(String firstName, String lastName, Date dob);

    // Given the person ID, get the person's first name
    String getFirstNameById(Integer id);

    // Given the person ID, get the person's last name
    String getLastNameById(Integer id);

    // Given the person ID, get the person's middle name
    String getMiddleNameById(Integer id);

    // Given the person ID, get the person's email
    String getEmailById(Integer id);

    // Given the person ID, get the person's cell phone number
    String getCellPhoneById(Integer id);

    // Given the person Id, get the person's Alt phone number
    String getAltPhoneById(Integer id);

    // Given the person ID, get the gender as a string depending if it is a 1 or 0
    String getGenderById(Integer id);

    // Get the person's SSN, by the person ID
    String getSSNById(Integer id);

    // Get the last four digits of the SSN, by person ID
    String getLastFourDigitSSNById(Integer id);

    // Get the person's Date of Birth by the person ID
    Date getDobById(Integer id);

    // Given the person ID, get the user ID
    int getUserIdById(Integer id);
}
