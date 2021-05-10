package entity;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="employee", schema = "backend")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "manager_id")
    private Integer managerId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "avatar")
    private File avatar;

    @Column(name = "car")
    private String car;

    @ManyToOne
    @JoinColumn(name = "visa_status_id")
    private VisaStatus visaStatus;

    @Column(name = "visa_start_date")
    private Date visaStartDate;

    @Column(name = "visa_end_date")
    private Date visaEndDate;

    @Column(name = "driver_license")
    private String driverLicense;

    @Column(name = "driver_license_exp_date")
    private Date driverLicenseExpDate;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.MERGE)
    List<PersonalDocument> personalDocuments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.MERGE)
    List<ApplicationWorkFlow> applicationWorkFlows = new ArrayList<>();
}
