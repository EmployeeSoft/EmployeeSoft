package com.example.demo.service;

import com.example.demo.dao.InterfaceEmployeeDao;
import com.example.demo.domain.EmployeeDomain;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class EmployeeService {
    private InterfaceEmployeeDao employeeDao;

    @Autowired
    public void setInterfaceEmployeeDao(InterfaceEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    private final String pattern = "yyyy-MM-dd";

    @Transactional
    public EmployeeDomain getEmployeeByEmployeeId(Integer id) {
        Employee employee = employeeDao.getEmployeeByEmployeeId(id);

        DateFormat df = new SimpleDateFormat(this.pattern);
        String date = df.format(employee.getDriverLicenseExpDate());

        int personId = employee.getPersonId();

        EmployeeDomain employeeDomain = EmployeeDomain.builder()
                .id(employee.getId())
                .title(employee.getTitle())
                .managerId(employee.getManagerId())
                .startDate(employee.getStartDate())
                .endDate(employee.getEndDate())
                .avatar(employee.getAvatar())
                .car(employee.getCar())
                .visaType(employeeDao.getVisaTypeByPersonId(personId))
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

    @Transactional
    public String getAvatarLink(Integer personId) {
        return employeeDao.getAvatarLink(personId);
    }

    @Transactional
    public String getVisaTypeByPersonId(Integer personId) {
        return employeeDao.getVisaTypeByPersonId(personId);
    }

    @Transactional
    public Date getVisaStartDateByPersonId(Integer personId) {
        return employeeDao.getVisaStartDateByPersonId(personId);
    }

    @Transactional
    public Date getVisaEndDateByPersonId(Integer personId) {
        return employeeDao.getVisaEndDateByPersonId(personId);
    }

    @Transactional
    public Date getEmployeeStartDateByPersonId(Integer personId) {
        return employeeDao.getEmployeeStartDateByPersonId(personId);
    }

    @Transactional
    public Date getEmployeeEndDateByPersonId(Integer personId) {
        return employeeDao.getEmployeeEndDateByPersonId(personId);
    }

    @Transactional
    public String getTitleByPersonId(Integer personId) {
        return employeeDao.getTitleByPersonId(personId);
    }

    @Transactional
    public String getCarByPersonId(Integer personId){
        return employeeDao.getCarByPersonId(personId);
    }

    @Transactional
    public EmployeeDomain getEmployeeByPersonId(Integer personId) {
        Employee employee = employeeDao.getEmployeeByPersonId(personId);

        DateFormat df = new SimpleDateFormat(this.pattern);
        String date = df.format(employee.getDriverLicenseExpDate());

        EmployeeDomain employeeDomain = EmployeeDomain.builder()
                .id(employee.getId())
                .title(employee.getTitle())
                .managerId(employee.getManagerId())
                .startDate(employee.getStartDate())
                .endDate(employee.getEndDate())
                .car(employee.getCar())
                .visaType(employeeDao.getVisaTypeByPersonId(personId))
                .visaStartDate(employee.getVisaStartDate())
                .visaEndDate(employee.getVisaEndDate())
                .driverLicense(employee.getDriverLicense())
                .driverLicenseExpDate(date)
                .build();

        return employeeDomain;
    }
}
