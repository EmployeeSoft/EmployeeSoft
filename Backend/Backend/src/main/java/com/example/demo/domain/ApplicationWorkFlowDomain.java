package com.example.demo.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationWorkFlowDomain {
    private Integer id;
//    private EmployeeDomain employeeDomain;
    private String dateCreated;
    private String dateModified;
    private Integer status;
    private String comment;
    private String type;
}
