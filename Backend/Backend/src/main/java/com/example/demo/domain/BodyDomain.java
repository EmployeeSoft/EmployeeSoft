package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BodyDomain {
    private PersonDomain personDomain;
    private EmployeeDomain employeeDomain;
    private AddressDomain addressDomain;
    private ContactDomain[] contactDomains;
}
