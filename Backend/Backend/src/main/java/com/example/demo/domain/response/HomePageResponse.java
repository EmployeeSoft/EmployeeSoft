package com.example.demo.domain.response;


import com.example.demo.domain.AddressDomain;
import com.example.demo.domain.ContactDomain;
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
    private ServiceStatus serviceStatus;
    private String fullName;
    private Date dob;
    private int age;
    private String gender;
    private String ssn;
    private ArrayList<AddressDomain> addressDomain;
    private ArrayList<ContactDomain> contactDomain;
}
