package com.example.demo.controller;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.transfer.Upload;
import com.example.demo.config.AWSConfig;
import com.example.demo.domain.*;
import com.example.demo.domain.common.ServiceStatus;
import com.example.demo.domain.response.HomePageResponse;
import com.example.demo.service.*;
import com.example.demo.util.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
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

    @Autowired
    private AWSS3Service awss3Service;

    @GetMapping("/user-info")
    public HomePageResponse getPersonalProfile(@RequestBody UserDomain userDomain) {
        // Creating return value
        HomePageResponse response = new HomePageResponse();

        // Getting user ID
        int userId = userDomain.getUserId();

        // Get the Person ID from User ID
        int personId = personService.getPersonIdByUserId(userId);

        PersonDomain personDomain = personService.getPersonByUserId(userId);
        EmployeeDomain employeeDomain = employeeService.getEmployeeByEmployeeId(personId);


        // Getting the user Role
        // The role can either be "employee" or "hr"
        String userRole = userDomain.getUserRole();


        /////  Name Section /////

        String firstName = personDomain.getFirstName();
        String middleName = personDomain.getMiddleName();
        String lastName = personDomain.getLastName();

        // Getting the Full name
        String fullName = firstName +
                " " + ((middleName == null)? "" : middleName + " ") +
                lastName;

        String preferName = personDomain.getPreferName();
        String avatar = employeeDomain.getAvatar();
        Date dob = personDomain.getDob();
        int age = CalculateAge.age(dob);
        String gender = personService.getGenderByUserId(userId);
        String ssn = personDomain.getSsn();


        ///// Address Section /////

        ArrayList<AddressDomain> addresses = addressService.getAddressListByPersonId(personId);


        ///// Contact information Section //////

        String email = personService.getEmailByUserId(userId);
        String cellPhone = personDomain.getCellPhone();
        String altPhone = personDomain.getAltPhone();


        ///// Employee Section /////

        String title = employeeDomain.getTitle();
        String car = employeeDomain.getCar();
        String visaType = employeeDomain.getVisaType();
        Date visaStartDate = employeeDomain.getVisaStartDate();
        Date visaEndDate = employeeDomain.getVisaEndDate();
        Date employeeStartDate = employeeDomain.getStartDate();
        Date employeeEndDate = employeeDomain.getEndDate();
        

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

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        return this.awss3Service.uploadFile(file);
    }
}
