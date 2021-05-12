package com.example.demo.controller;

import com.example.demo.domain.AddressDomain;
import com.example.demo.domain.ContactDomain;
import com.example.demo.domain.PersonDomain;
import com.example.demo.domain.common.ServiceStatus;
import com.example.demo.domain.response.HomePageResponse;
import com.example.demo.service.*;
import com.example.demo.util.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;

@RestController
public class MainController {
//    @Autowired
//    private EmployeeService employeeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private PersonService personService;

    @Autowired
    private VisaStatusService visaStatusService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/user-info/{userId}")
    public HomePageResponse getHomePage(@PathVariable Integer userId) {
        HomePageResponse response = new HomePageResponse();

        // Since the user is able to access this method, it means that they are authorized

        PersonDomain person = personService.getPersonByUserId(userId);

        // Get full name
        String fullName = person.getFirstName() +
                " " + ((person.getMiddleName() != null)? "" : person.getMiddleName() + " ") +
                person.getLastName();

        // Getting the date of birth
        Date dob = person.getDob();

        // Getting user's age
        int age = CalculateAge.age(dob);

        // Getting user's gender
        String gender = personService.getGenderByUserId(userId);

        // Getting the last four digit of user's SSN
        String ssn = personService.getLastFourDigitSSNByUserId(userId);

        // Get the Person ID from User ID
        int personId = personService.getPersonIdByUserId(userId);

        // Get the Visa Status
//        boolean isStatusManagement = visaStatusService.visaStatusManagementAble();

        // Getting an array of user's addresses
        ArrayList<AddressDomain> addresses = addressService.getAddressListByPersonId(personId);

        // Getting an array of user's contacts;
        ArrayList<ContactDomain> contacts = contactService.getContactListByPersonId(personId);

        // Save information into response to return

        // 
        response.setServiceStatus(new ServiceStatus("Success", true, ""));
        response.setFullName(fullName);
        response.setDob(dob);
        response.setAge(age);
        response.setGender(gender);
        response.setSsn(ssn);
        response.setAddressDomain(addresses);
        response.setContactDomain(contacts);

        return response;
    }
}
