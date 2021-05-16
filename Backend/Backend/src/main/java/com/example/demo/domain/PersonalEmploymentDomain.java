package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalEmploymentDomain {
    private Integer id;
    private Integer personId;
    private String title;
    private Integer managerId;
    private String startDate;
    private String endDate;
    private String avatar;
    private String car;
    private Integer visaStatusId;
    private String visaStartDate;
    private String visaEndDate;
    private String driverLicense;
    private String driverLicenseExpDate;
}
