package com.example.demo.service;

import com.example.demo.dao.InterfaceEmployeeDao;
import com.example.demo.dao.implementation.EmployeeDao;
import com.example.demo.domain.EmployeeDomain;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class EmployeeService {
    private InterfaceEmployeeDao employeeDao;

    @Autowired
    private PersonalDocService personalDocService;

    @Autowired
    private AppWorkFlowService appWorkFlowService;

    @Autowired
    private VisaStatusService visaStatusService;

    @Autowired
    private PersonService personService;

    @Autowired
    public void setInterfaceEmployeeDao(InterfaceEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional
    public EmployeeDomain getEmployeeByEmployeeId(Integer id) {
        Employee employee = employeeDao.getEmployeeByEmployeeId(id);

        EmployeeDomain employeeDomain = EmployeeDomain.builder()
                .id(employee.getId())
                .title(employee.getTitle())
                .managerId(employee.getManagerId())
                .startDate(employee.getStartDate())
                .endDate(employee.getEndDate())
                .avatar(employee.getAvatar())
                .car(employee.getCar())
                .visaStatusDomain(visaStatusService.getVisaStatusById(employee.getVisaStatus()))
                .visaType(employeeDao.getVisaTypeByPersonId(employee.getPersonId()))
                .visaStartDate(employee.getVisaStartDate())
                .visaEndDate(employee.getVisaEndDate())
                .driverLicense(employee.getDriverLicense())
                .driverLicenseExpDate(employee.getDriverLicenseExpDate())
                .personalDocumentDomain(personalDocService.getPersonalDocsByEmployeeId(employee.getId()))
                .applicationWorkFlowDomain(appWorkFlowService.getAppWorkFlowsByEmployeeId(employee.getId()))
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
    public boolean isStatusManagementAble(Integer personId) {
        return employeeDao.isStatusManagementAble(personId);
    }

    @Transactional
    public EmployeeDomain getEmployeeByPersonId(Integer personId) {
        Employee employee = employeeDao.getEmployeeByPersonId(personId);

        EmployeeDomain employeeDomain = EmployeeDomain.builder()
                .id(employee.getId())
                .title(employee.getTitle())
                .managerId(employee.getManagerId())
                .startDate(employee.getStartDate())
                .endDate(employee.getEndDate())
                .avatar(employee.getAvatar())
                .car(employee.getCar())
                .visaStatusDomain(visaStatusService.getVisaStatusById(employee.getVisaStatus()))
                .visaType(employeeDao.getVisaTypeByPersonId(employee.getPersonId()))
                .visaStartDate(employee.getVisaStartDate())
                .visaEndDate(employee.getVisaEndDate())
                .driverLicense(employee.getDriverLicense())
                .driverLicenseExpDate(employee.getDriverLicenseExpDate())
                .personalDocumentDomain(personalDocService.getPersonalDocsByEmployeeId(employee.getId()))
                .applicationWorkFlowDomain(appWorkFlowService.getAppWorkFlowsByEmployeeId(employee.getId()))
                .build();

        return employeeDomain;
    }

    @Transactional
    public void updateEmployeeAvatarByPersonId(Integer personId, String avatar) {
        employeeDao.updateEmployeeAvatarByPersonId(personId, avatar);
    }

    @Transactional
    public int getEmployeeIdByPersonId(Integer personId) {
        return employeeDao.getEmployeeIdByPersonId(personId);
    }

    @Transactional
    public ArrayList<EmployeeDomain> getAllEmployees() {
        // Return
        ArrayList<EmployeeDomain> employeeDomains = new ArrayList<>();

        // Convert Employee to employeeDomain and save to employeeDomains
        for (Employee employee : employeeDao.getAllEmployees()) {
            int userId = personService.getUserIdByPersonId(employee.getPersonId());

            EmployeeDomain domain = EmployeeDomain.builder()
                    .id(employee.getId())
                    .title(employee.getTitle())
                    .managerId(employee.getManagerId())
                    .startDate(employee.getStartDate())
                    .endDate(employee.getEndDate())
                    .avatar(employee.getAvatar())
                    .car(employee.getCar())
                    .visaStatusDomain(visaStatusService.getVisaStatusById(employee.getVisaStatus()))
                    .visaType(employeeDao.getVisaTypeByPersonId(employee.getPersonId()))
                    .visaStartDate(employee.getVisaStartDate())
                    .visaEndDate(employee.getVisaEndDate())
                    .driverLicense(employee.getDriverLicense())
                    .driverLicenseExpDate(employee.getDriverLicenseExpDate())
                    .personDomain(personService.getPersonByUserId(userId))
                    .personalDocumentDomain(personalDocService.getPersonalDocsByEmployeeId(employee.getId()))
                    .applicationWorkFlowDomain(appWorkFlowService.getAppWorkFlowsByEmployeeId(employee.getId()))
                    .build();

            employeeDomains.add(domain);
        }

        return employeeDomains;
    }
}
