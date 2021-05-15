package com.example.demo.domain.response;

import com.example.demo.domain.EmployeeDomain;
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
public class AllEmployeeResponse {
    // Http Section
    private ServiceStatus serviceStatus;

    // List of all employees
    ArrayList<EmployeeDomain> employees;
}
