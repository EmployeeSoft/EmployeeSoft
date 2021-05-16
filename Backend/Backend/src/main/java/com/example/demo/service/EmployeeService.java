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

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int userId = personService.getUserIdByPersonId(employee.getPersonId());

        EmployeeDomain employeeDomain = EmployeeDomain.builder()
                .id(employee.getId())
                .title(employee.getTitle())
                .managerId(employee.getManagerId())
                .startDate(df.format(employee.getStartDate()))
                .endDate(df.format(employee.getEndDate()))
                .avatar(employee.getAvatar())
                .car(employee.getCar())
                .visaStatusDomain(visaStatusService.getVisaStatusById(employee.getVisaStatusId()))
                .visaType(employeeDao.getVisaTypeByPersonId(employee.getPersonId()))
                .visaStartDate(df.format(employee.getVisaStartDate()))
                .visaEndDate(df.format(employee.getVisaEndDate()))
                .driverLicense(employee.getDriverLicense())
                .driverLicenseExpDate(df.format(employee.getDriverLicenseExpDate()))
                .personDomain(personService.getPersonByUserId(userId))
                .personalDocumentDomains(personalDocService.getPersonalDocsByEmployeeId(employee.getId()))
                .applicationWorkFlowDomains(appWorkFlowService.getAppWorkFlowsByEmployeeId(employee.getId()))
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
        int userId = personService.getUserIdByPersonId(employee.getPersonId());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        EmployeeDomain employeeDomain = EmployeeDomain.builder()
                .id(employee.getId())
                .title(employee.getTitle())
                .managerId(employee.getManagerId())
                .startDate(df.format(employee.getStartDate()))
                .endDate(df.format(employee.getEndDate()))
                .avatar(employee.getAvatar())
                .car(employee.getCar())
                .visaStatusDomain(visaStatusService.getVisaStatusById(employee.getVisaStatusId()))
                .visaType(employeeDao.getVisaTypeByPersonId(employee.getPersonId()))
                .visaStartDate(df.format(employee.getVisaStartDate()))
                .visaEndDate(df.format(employee.getVisaEndDate()))
                .driverLicense(employee.getDriverLicense())
                .driverLicenseExpDate(df.format(employee.getDriverLicenseExpDate()))
                .personDomain(personService.getPersonByUserId(userId))
                .personalDocumentDomains(personalDocService.getPersonalDocsByEmployeeId(employee.getId()))
                .applicationWorkFlowDomains(appWorkFlowService.getAppWorkFlowsByEmployeeId(employee.getId()))
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

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        // Convert Employee to employeeDomain and save to employeeDomains
        for (Employee employee : employeeDao.getAllEmployees()) {
            int userId = personService.getUserIdByPersonId(employee.getPersonId());

            EmployeeDomain domain = EmployeeDomain.builder()
                    .id(employee.getId())
                    .title(employee.getTitle())
                    .managerId(employee.getManagerId())
                    .startDate(df.format(employee.getStartDate()))
                    .endDate(df.format(employee.getEndDate()))
                    .avatar(employee.getAvatar())
                    .car(employee.getCar())
                    .visaStatusDomain(visaStatusService.getVisaStatusById(employee.getVisaStatusId()))
                    .visaType(employeeDao.getVisaTypeByPersonId(employee.getPersonId()))
                    .visaStartDate(df.format(employee.getVisaStartDate()))
                    .visaEndDate(df.format(employee.getVisaEndDate()))
                    .driverLicense(employee.getDriverLicense())
                    .driverLicenseExpDate(df.format(employee.getDriverLicenseExpDate()))
                    .personDomain(personService.getPersonByUserId(userId))
                    .personalDocumentDomains(personalDocService.getPersonalDocsByEmployeeId(employee.getId()))
                    .applicationWorkFlowDomains(appWorkFlowService.getAppWorkFlowsByEmployeeId(employee.getId()))
                    .build();

            employeeDomains.add(domain);
        }

        return employeeDomains;
    }

    @Transactional
    public int getEmployeeIdByUserId(Integer userId) {
        return employeeDao.getEmployeeIdByUserId(userId);
    }

}
