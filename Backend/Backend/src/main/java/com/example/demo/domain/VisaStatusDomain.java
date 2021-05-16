package com.example.demo.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisaStatusDomain {
    private Integer id;
    private String visaType;
    private Boolean isActive;
    private String dateModified;
    private String createUser;
}
