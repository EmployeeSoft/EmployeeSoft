package com.example.demo.domain;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDomain {
    private Integer id;
    private String title;
    private Integer managerId;
    private String startDate;
    private String endDate;
    private String avatar;
    private String car;
    private VisaStatusDomain visaStatusDomain;
    private String visaType;
    private String visaStartDate;
    private String visaEndDate;
    private String driverLicense;
    private String driverLicenseExpDate;
    private PersonDomain personDomain;
    private PersonalDocumentDomain personalDocumentDomain;
    private ApplicationWorkFlowDomain applicationWorkFlowDomain;
    private Integer visaStatusId;
    private ArrayList<PersonalDocumentDomain> personalDocumentDomains;
    private ArrayList<ApplicationWorkFlowDomain> applicationWorkFlowDomains;
}
