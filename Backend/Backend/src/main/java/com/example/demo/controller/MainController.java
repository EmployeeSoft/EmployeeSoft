package com.example.demo.controller;

import com.example.demo.domain.AddressDomain;
import com.example.demo.domain.ContactDomain;
import com.example.demo.domain.UserDomain;
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
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private PersonService personService;

    @Autowired
    private VisaStatusService visaStatusService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/user-info")
    public HomePageResponse getPersonalProfile(@RequestBody UserDomain userDomain) {
        // Creating return value
        HomePageResponse response = new HomePageResponse();

        // Getting user ID
        int userId = userDomain.getUserId();

        // Get the Person ID from User ID
        int personId = personService.getPersonIdByUserId(userId);


        // Getting the user Role
        // The role can either be "employee" or "hr"
        String userRole = userDomain.getUserRole();


        /////  Name Section /////

        String firstName = personService.getFirstNameByUserId(userId);
        String middleName = personService.getMiddleNameByUserId(userId);
        String lastName = personService.getLastNameByUserId(userId);

        // Getting the Full name
        String fullName = firstName +
                " " + ((middleName == null)? "" : middleName + " ") +
                lastName;

        String preferName = personService.getPreferNameByUserId(userId);
        String avatar = employeeService.getAvatarLink(personId);
        Date dob = personService.getDobByUserId(userId);
        int age = CalculateAge.age(dob);
        String gender = personService.getGenderByUserId(userId);
        String ssn = personService.getLastFourDigitSSNByUserId(userId);


        ///// Address Section /////

        ArrayList<AddressDomain> addresses = addressService.getAddressListByPersonId(personId);


        ///// Contact information Section //////

        String email = personService.getEmailById(userId);
        String cellPhone = personService.getCellPhoneByUserId(userId);
        String altPhone = personService.getAltPhoneByUserId(userId);


        ///// Employee Section /////

        String title = employeeService.getTitleByPersonId(personId);
        String car = employeeService.getCarByPersonId(personId);
        String visaType = employeeService.getVisaTypeByPersonId(personId);
        Date visaStartDate = employeeService.getVisaStartDateByPersonId(personId);
        Date visaEndDate = employeeService.getVisaEndDateByPersonId(personId);
        Date employeeStartDate = employeeService.getEmployeeStartDateByPersonId(personId);
        Date employeeEndDate = employeeService.getEmployeeEndDateByPersonId(personId);



        ///// Emergency Information Section /////

        ArrayList<ContactDomain> contacts = contactService.getContactListByPersonId(personId);


        ///// Document Section /////
        // TODO


        // Save response
        response.setServiceStatus(new ServiceStatus("Success", true, ""));

        // Name Section
        response.setFullName(fullName);
        response.setPreferName(preferName);
        response.setAvatar(avatar);
        response.setDob(dob);
        response.setAge(age);
        response.setGender(gender);
        response.setSsn(ssn);

        // Address Section
        response.setAddress(addresses);

        // Contact information section
        response.setEmail(email);
        response.setCellphone(cellPhone);
        response.setAltPhone(altPhone);

        // Employee Section
        response.setTitle(title);
        response.setCar(car);
        response.setVisaType(visaType);
        response.setVisaStartDate(visaStartDate);
        response.setVisaEndDate(visaEndDate);
        response.setEmployeeStartDate(employeeStartDate);
        response.setEmployeeEndDate(employeeEndDate);

        // Emergency Contact Section
        response.setContracts(contacts);

        return response;
    }
}
