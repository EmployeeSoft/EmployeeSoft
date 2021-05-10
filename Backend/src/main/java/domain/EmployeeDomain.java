package domain;

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
    //private File avatar;
    private String car;
    private VisaStatusDomain visaStatusDomain;
    private Date visaStartDate;
    private Date visaEndDate;
    private String driverLicense;
    private String driverLicenseExpDate;
    private PersonDomain personDomain;
    private PersonalDocumentDomain personalDocumentDomain;
    private ApplicationWorkFlowDomain applicationWorkFlowDomain;
}
