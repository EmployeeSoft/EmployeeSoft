package com.example.demo.domain.response;


import com.example.demo.domain.AddressDomain;
import com.example.demo.domain.EmployeeDomain;
import com.example.demo.domain.common.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class HomePageResponse {
    private ServiceStatus serviceStatus;
//    private AddressDomain addressDomain;
    private EmployeeDomain employeeDomain;
}
