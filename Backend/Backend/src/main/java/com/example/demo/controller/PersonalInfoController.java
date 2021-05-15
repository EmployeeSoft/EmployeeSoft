package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.domain.common.ServiceStatus;
import com.example.demo.domain.response.HomePageResponse;
import com.example.demo.domain.response.PersonalInfoResponse;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PersonalInfoController {
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

    @PostMapping("/person/address")
    public PersonalInfoResponse updateAddress(@RequestBody PersonalAddressDomain addressDomain) {
        PersonalInfoResponse infoResponse = new PersonalInfoResponse();
        Integer userId = addressDomain.getId();
        String primaryAddress = addressDomain.getPrimaryAddress();
        String secondAddress = addressDomain.getSecondAddress();
        if(personService.updateAddressByUserId(userId, primaryAddress, secondAddress)){
            infoResponse.setServiceStatus(new ServiceStatus("Success", true, ""));
        } else {
            infoResponse.setServiceStatus(new ServiceStatus("Failed", false, "Unable to update address"));
        }

        return infoResponse;
    }

    @PostMapping("/person/contact")
    public PersonalInfoResponse updateContact(@RequestBody PersonalContactDomain contactDomain) {
        PersonalInfoResponse infoResponse = new PersonalInfoResponse();
        if(personService.updateContact(contactDomain)){
            infoResponse.setServiceStatus(new ServiceStatus("Success", true, ""));
        } else {
            infoResponse.setServiceStatus(new ServiceStatus("Failed", false, "Unable to update address"));
        }

        return infoResponse;
    }

//    @PostMapping("/person/emergency")
//    public PersonalInfoResponse updateEmergency(@RequestBody PersonalEmergencyDomain emergencyDomain) {
//        PersonalInfoResponse infoResponse = new PersonalInfoResponse();
//        if(personService.updateEmergency(emergencyDomain)){
//            infoResponse.setServiceStatus(new ServiceStatus("Success", true, ""));
//        } else {
//            infoResponse.setServiceStatus(new ServiceStatus("Failed", false, "Unable to update address"));
//        }
//
//        return infoResponse;
//    }

    @PostMapping("/person/employment")
    public PersonalInfoResponse updateEmployment(@RequestBody PersonalEmploymentDomain domain) {
        PersonalInfoResponse infoResponse = new PersonalInfoResponse();
        if(personService.updateEmployment(domain)){
            infoResponse.setServiceStatus(new ServiceStatus("Success", true, ""));
        } else {
            infoResponse.setServiceStatus(new ServiceStatus("Failed", false, "Unable to update address"));
        }

        return infoResponse;
    }

    @PostMapping("/person/name")
    public PersonalInfoResponse updatePersonInfo(@RequestBody PersonDomain domain) {
        PersonalInfoResponse infoResponse = new PersonalInfoResponse();
        if(personService.updatePersonInfo(domain)){
            infoResponse.setServiceStatus(new ServiceStatus("Success", true, ""));
        } else {
            infoResponse.setServiceStatus(new ServiceStatus("Failed", false, "Unable to update address"));
        }

        return infoResponse;
    }
}
