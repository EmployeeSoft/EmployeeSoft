package com.example.demo.dao;

import com.example.demo.domain.*;
import com.example.demo.entity.Person;

import java.sql.Date;

public interface InterfacePersonDao {
    // Given the ID, get the person's information
    Person getPersonById(Integer id);

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

    // Get the last four digits of the SSN, by person ID
    String getLastFourDigitSSNById(Integer id);

    // Get the person's Date of Birth by the person ID
    Date getDobById(Integer id);

    // Get the person's information from the user ID
    Person getPersonByUserId(Integer userId);

    // Given the user ID, get the person's gender and return either Male or female
    String getGenderByUserId(Integer userId);

    // Get the last four digits of the SSN, by user ID
    String getLastFourDigitSSNByUserId(Integer userId);

    // Given the user ID get the person ID
    int getPersonIdByUserId(Integer userId);

    // Get person's first name by user ID
    String getFirstNameByUserId(Integer userId);

    // Get person's middle name by user ID
    String getMiddleNameByUserId(Integer userId);

    // Get person's last name by user ID
    String getLastNameByUserId(Integer userId);

    // Get person's email by user ID
    String getEmailByUserId(Integer userId);

    // Get the person's cell phone number by user ID
    String getCellPhoneByUserId(Integer userId);

    // Get the person's alt phone number by user ID
    String getAltPhoneByUserId(Integer userId);

    // Get the person's preferred name by user ID
    String getPreferNameByUserId(Integer userId);

    // Get the person's DoB by user ID
    Date getDobByUserId(Integer userId);

    // Update the person's Name
    boolean updatePersonInfo(PersonDomain personDomain);

    Person addNewPerson(Person person);

    // Get the user ID from the person ID
    int getUserIdByPersonId(Integer personId);
}
