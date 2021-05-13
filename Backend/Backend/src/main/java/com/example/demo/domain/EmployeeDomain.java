package com.example.demo.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDomain {
    private Integer id;
    private String title;
    private Integer managerId;
    private Date startDate;
    private Date endDate;
    private String avatar;
    private String car;
    private VisaStatusDomain visaStatusDomain;
    private String visaType;
    private Date visaStartDate;
    private Date visaEndDate;
    private String driverLicense;
    private String driverLicenseExpDate;
    private PersonDomain personDomain;
    private PersonalDocumentDomain personalDocumentDomain;
    private ApplicationWorkFlowDomain applicationWorkFlowDomain;
}
