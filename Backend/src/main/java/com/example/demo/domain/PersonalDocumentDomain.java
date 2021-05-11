package com.example.demo.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalDocumentDomain {
    private Integer id;
    private EmployeeDomain employeeDomain;
    private String path;
    private String title;
    private String comment;
    private Date dateCreated;
    private String createdBy;
}
