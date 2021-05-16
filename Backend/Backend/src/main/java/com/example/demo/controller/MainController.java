package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.domain.common.ServiceStatus;
import com.example.demo.domain.response.*;
import com.example.demo.entity.Person;
import com.example.demo.service.*;
import com.example.demo.util.CalculateAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:9999"}, allowCredentials = "true")
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

    @Autowired
    private OnBoardService onBoardService;

    @Autowired
    private AppWorkFlowService appWorkFlowService;

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
        String dob = personDomain.getDob();
        int age = CalculateAge.age(personDomain.getDob());
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

        String visaStartDate = employeeDomain.getVisaStartDate();
        String visaEndDate = employeeDomain.getVisaEndDate();
        String employeeStartDate = employeeDomain.getStartDate();
        String employeeEndDate = employeeDomain.getEndDate();        

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
    public UploadResponse fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId,
                                     @RequestParam("uploadTo") String uploadTo, @RequestParam("fileTitle") String fileTitle) {

        /*
            uploadTo is used to distinguish if the file will be upload to the employee table for avatar pic
                or
            upload to the personal_doc table for personal documents

            uploadTo can either be "avatar" or "personal document"
        */

        String filename = file.getOriginalFilename();
        UploadResponse response = new UploadResponse();

        if (awss3Service.uploadFile(file, uploadTo, userId, fileTitle)) {
            response.setServiceStatus(new ServiceStatus("Success", true, ""));
            response.setUrl(awss3Service.getURL(userId + "_" + filename));
        } else {
            response.setServiceStatus(new ServiceStatus("Failed", false, "Unable to upload file"));
        }

        return response;
    }

    @PostMapping("/onboard")
    public OnBoardResponse onboard(@RequestBody BodyDomain bodyDomain) {
        OnBoardResponse response = new OnBoardResponse();

        PersonDomain personDomain = bodyDomain.getPersonDomain();
        EmployeeDomain employeeDomain = bodyDomain.getEmployeeDomain();
        AddressDomain addressDomain = bodyDomain.getAddressDomain();
        ContactDomain[] contactDomains = bodyDomain.getContactDomains();

        Person person = onBoardService.addNewPerson(personDomain);
        int employeeId = onBoardService.addNewEmployee(employeeDomain, person);
        onBoardService.addNewAddress(addressDomain, person);
        for (ContactDomain contactDomain : contactDomains) {
            onBoardService.addNewContact(contactDomain, person);
        }

        response.setServiceStatus(new ServiceStatus("SUCCESS", true, ""));
        return response;
    }

    @GetMapping("/all-employees")
    public AllEmployeeResponse getAllEmployees(@RequestBody AllEmployeeDomain allEmployeeDomain) {
        // Return type
        AllEmployeeResponse response = new AllEmployeeResponse();
        String userRole = allEmployeeDomain.getUserRole();

        if (userRole.equals("hr")) {
            response.setEmployees(employeeService.getAllEmployees());
            response.setServiceStatus(new ServiceStatus("Success", true, ""));
        } else {
            String errorMsg = "You are not authorized";
            response.setServiceStatus(new ServiceStatus("Fail", false, errorMsg));
        }

        return response;
    }

    @GetMapping("/download")
    public DownloadFileResponse downloadFromS3(@RequestParam Integer userId,
                                               @RequestParam String filename) {
        DownloadFileResponse response = new DownloadFileResponse();

        ByteArrayResource data = awss3Service.downloadFile(userId, filename);

        // Check if there are data to be returned
        if (data != null) {
            response.setServiceStatus(new ServiceStatus("Success", true, ""));
            response.setFile(data);
        } else {
            String errorMsg = "Unable to retrieve file";
            response.setServiceStatus(new ServiceStatus("Fail", false, errorMsg));
        }
        return response;
    }

    @GetMapping("/employee-visa-status")
    public VisaStatusResponse getVisaStatus(@RequestBody VisaStatusRequestDomain vsrd) {
        VisaStatusResponse response = new VisaStatusResponse();

        // Used to grab the information where user ID = userId
        int userId = vsrd.getUserId();
        String userRole = vsrd.getUserRole();

        if (userRole.equals("employee")) {
            if (appWorkFlowService.checkEmployeeAppWorkFlowExist(userId)) {
                response.setComment(appWorkFlowService.getComment(userId));
                response.setType(appWorkFlowService.getType(userId));
                response.setServiceStatus(new ServiceStatus("Success", true, ""));
            } else {
                String errorMsg = "User ID " + userId + " has not uploaded any files yet";
                response.setServiceStatus(new ServiceStatus("Fail", false, errorMsg));
            }
        }

        return response;
    }

    // Will replace /user-info

    @GetMapping("/employee")
    public HomePageResponse getPersonalProfile(@RequestParam(value="userRole") String role, @RequestParam(value="userId") String id) {
        // Creating return value
        HomePageResponse response = new HomePageResponse();

        // Getting user ID
        int userId = Integer.parseInt(id);

        // Get the Person ID from User ID
        int personId = personService.getPersonIdByUserId(userId);

        PersonDomain personDomain = personService.getPersonByUserId(userId);
        EmployeeDomain employeeDomain = employeeService.getEmployeeByEmployeeId(personId);


        // Getting the user Role
        // The role can either be "employee" or "hr"
        String userRole = role;


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
        String dob = personDomain.getDob();
        int age = CalculateAge.age(personDomain.getDob());
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

        String visaStartDate = employeeDomain.getVisaStartDate();
        String visaEndDate = employeeDomain.getVisaEndDate();
        String employeeStartDate = employeeDomain.getStartDate();
        String employeeEndDate = employeeDomain.getEndDate();

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
