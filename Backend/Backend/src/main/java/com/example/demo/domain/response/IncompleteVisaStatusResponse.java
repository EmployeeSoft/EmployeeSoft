package com.example.demo.domain.response;

import com.example.demo.domain.EmployeeDomain;
import com.example.demo.domain.IncompleteEmployeeDomain;
import com.example.demo.domain.common.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncompleteVisaStatusResponse {
    // Http Section
    private ServiceStatus serviceStatus;

    // Array holding the employees information
    private ArrayList<IncompleteEmployeeDomain> employees;
}
