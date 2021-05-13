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
    private Date dob;
    private int age;
    private String gender;
    private String ssn; // Only last four digits

    // Address Section
    private ArrayList<AddressDomain> address;

    // Contact information Section
    private String email;
    private String cellphone;
    private String altPhone;

    // Employment Section
    private String title;
    private String car;
    private String visaType;
    private Date visaStartDate;
    private Date visaEndDate;
    private Date employeeStartDate;
    private Date employeeEndDate;

    // Emergency Contact Section
    private ArrayList<ContactDomain> contracts;

    // Document Section
//    private ArrayList<PersonalDocumentDomain> documents;
}
