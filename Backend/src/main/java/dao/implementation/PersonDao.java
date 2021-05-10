package dao.implementation;

import dao.InterfacePersonDao;
import entity.Person;

import java.sql.Date;

public class PersonDao implements InterfacePersonDao {
    // Given the ID, get the person's information
    public Person getPersonById(Integer id) {
        // TODO
        return new Person();
    }

    // Given the person's SSN, get the person's information
    public Person getPersonBySSN(String ssn) {
        // TODO
        return new Person();
    }

    // Given the person's first name, last name, and date of birth, get the person information
    public Person getPersonByInfo(String firstName, String lastName, Date dob) {
        // TODO
        return new Person();
    }

    // Given the person ID, get the person's first name
    public String getFirstNameById(Integer id) {
        // TODO
        return "";
    }

    // Given the person ID, get the person's last name
    public String getLastNameById(Integer id) {
        // TODO
        return "";
    }

    // Given the person ID, get the person's middle name
    public String getMiddleNameById(Integer id) {
        // TODO
        return "";
    }

    // Given the person ID, get the person's email
    public String getEmailById(Integer id) {
        // TODO
        return "";
    }

    // Given the person ID, get the person's cell phone number
    public String getCellPhoneById(Integer id) {
        // TODO
        return "";
    }

    // Given the person Id, get the person's Alt phone number
    public String getAltPhoneById(Integer id) {
        // TODO
        return "";
    }

    // Given the person ID, get the gender as a string depending if it is a 1 or 0
    public String getGenderById(Integer id) {
        // TODO
        return "";
    }

    // Get the person's SSN, by the person ID
    public String getSSNById(Integer id) {
        // TODO
        return "";
    }

    // Get the last four digits of the SSN, by person ID
    public String getLastFourDigitSSNById(Integer id) {
        // TODO
        return "";
    }

    // Get the person's Date of Birth by the person ID
    public Date getDobById(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the person ID, get the user ID
    public int getUserIdById(Integer id) {
        // TODO
        return 0;
    }
}
