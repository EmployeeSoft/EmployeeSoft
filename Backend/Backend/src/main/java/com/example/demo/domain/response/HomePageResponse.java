package com.example.demo.domain.response;


import com.example.demo.domain.AddressDomain;
import com.example.demo.domain.ContactDomain;
import com.example.demo.domain.PersonalDocumentDomain;
import com.example.demo.domain.common.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class HomePageResponse {
    // Http Section
    private ServiceStatus serviceStatus;

    // Name Section
    private String fullName;
    private String preferName;
    private String avatar;
    private String dob;
    private int age;
    private String gender;
    private String ssn; // Only last four digits

    // Address Section
    private ArrayList<AddressDomain> address;
    private int personId;

    // Contact information Section
    private String email;
    private String cellphone;
    private String altPhone;

    // Employment Section
    private String title;
    private String car;
    private String visaType;
    private String visaStartDate;
    private String visaEndDate;
    private String employeeStartDate;
    private String employeeEndDate;
    private String driverLicense;
    private String driverLicenseExpDate;

    // Emergency Contact Section
    private ArrayList<ContactDomain> contracts;

    // Document Section
//    private ArrayList<PersonalDocumentDomain> documents;
}
