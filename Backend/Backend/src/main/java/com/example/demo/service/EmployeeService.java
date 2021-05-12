package com.example.demo.service;

import com.example.demo.dao.InterfaceEmployeeDao;
import com.example.demo.domain.EmployeeDomain;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class EmployeeService {
    private InterfaceEmployeeDao employeeDao;

    @Autowired
    public void setInterfaceEmployeeDao(InterfaceEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional
    public EmployeeDomain getEmployeeByEmployeeId(Integer id) {
        Employee employee = employeeDao.getEmployeeByEmployeeId(id);

        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String date = df.format(employee.getDriverLicenseExpDate());

        EmployeeDomain employeeDomain = EmployeeDomain.builder()
                .id(employee.getId())
                .title(employee.getTitle())
                .managerId(employee.getManagerId())
                .startDate(employee.getStartDate())
                .endDate(employee.getEndDate())
                .car(employee.getCar())
                .visaStartDate(employee.getVisaStartDate())
                .visaEndDate(employee.getVisaEndDate())
                .driverLicense(employee.getDriverLicense())
                .driverLicenseExpDate(date)
                .build();

        return employeeDomain;
    }

    @Transactional
    public int getManagerIdByEmployeeId(Integer id) {
        return employeeDao.getManagerIdByEmployeeId(id);
    }
}
