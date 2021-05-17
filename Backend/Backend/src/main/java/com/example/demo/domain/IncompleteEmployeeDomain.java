package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class IncompleteEmployeeDomain {
    // Employee's name
    private String name;

    // Employee's work authorization
    private String workAuthorization;

    // Employee's F1/OPT, F1/OPT STEM expirationDate
    private String expirationDate;

    // Number of days before expiration
    private long daysLeft;
}
