package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalEmploymentDomain {
    private Integer person_id;
    private String title;
    private Integer manager_id;
    private String start_date;
    private String end_date;
    private String avatar;
    private String car;
    private Integer visa_status_id;
    private String visa_start_date;
    private String visa_end_date;
    private String driver_license;
    private String driver_license_exp_date;
}
