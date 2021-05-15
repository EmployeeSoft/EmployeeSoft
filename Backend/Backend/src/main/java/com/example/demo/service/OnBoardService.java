package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.domain.AddressDomain;
import com.example.demo.domain.ContactDomain;
import com.example.demo.domain.EmployeeDomain;
import com.example.demo.domain.PersonDomain;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class OnBoardService {
    private InterfacePersonDao interfacePersonDao;
    private InterfaceEmployeeDao interfaceEmployeeDao;
    private InterfaceAddressDao interfaceAddressDao;
    private InterfaceContactDao interfaceContactDao;
    private InterfaceVisaStatusDao interfaceVisaStatusDao;

    @Autowired
    public void setInterfacePersonDao(InterfacePersonDao interfacePersonDao) {
        this.interfacePersonDao = interfacePersonDao;
    }

    @Autowired
    public void setInterfaceEmployeeDao(InterfaceEmployeeDao interfaceEmployeeDao) {
        this.interfaceEmployeeDao = interfaceEmployeeDao;
    }

    @Autowired
    public void setInterfaceAddressDao(InterfaceAddressDao interfaceAddressDao) {
        this.interfaceAddressDao = interfaceAddressDao;
    }

    @Autowired
    public void setInterfaceContactDao(InterfaceContactDao interfaceContactDao) {
        this.interfaceContactDao = interfaceContactDao;
    }

    @Autowired
    public void setInterfaceVisaStatusDao(InterfaceVisaStatusDao interfaceVisaStatusDao) {
        this.interfaceVisaStatusDao = interfaceVisaStatusDao;
    }

    @Transactional
    public Person addNewPerson(PersonDomain personDomain) {
        System.out.println(personDomain.getDob());
        Person person = new Person().builder()
                .firstName(personDomain.getFirstName())
                .lastName(personDomain.getLastName())
                .middleName(personDomain.getMiddleName())
                .preferName(personDomain.getPreferName())
                .email(personDomain.getEmail())
                .cellPhone(personDomain.getCellPhone())
                .altPhone(personDomain.getAltPhone())
                .gender(personDomain.getGender())
                .dob(Date.valueOf(personDomain.getDob()))
                .userId(personDomain.getUserId())
                .ssn(personDomain.getSsn())
                .build();

        return interfacePersonDao.addNewPerson(person);
    }

    @Transactional
    public int addNewEmployee(EmployeeDomain employeeDomain, Person person) {
        VisaStatus visaStatus = interfaceVisaStatusDao.getVisaStatusById(employeeDomain.getVisaStatusId());

        System.out.println("Person ID: " + person.getId());
        Employee employee = new Employee().builder()
                .person(person)
                .personId(person.getId())
                .avatar(" ")
                .title("Employee")
                .managerId(1)
                .startDate(new Date(System.currentTimeMillis()))
                .endDate(new Date(System.currentTimeMillis()))
                .car(employeeDomain.getCar())
                .visaStatus(visaStatus)
                .driverLicense(employeeDomain.getDriverLicense())
                .build();

        if (!employeeDomain.getVisaStartDate().equals("")) {employee.setVisaStartDate(Date.valueOf(employeeDomain.getVisaStartDate()));}
        if (!employeeDomain.getVisaEndDate().equals("")) {employee.setVisaEndDate(Date.valueOf(employeeDomain.getVisaEndDate()));}
        if (!employeeDomain.getDriverLicenseExpDate().equals("")) {employee.setDriverLicenseExpDate(Date.valueOf(employeeDomain.getDriverLicenseExpDate()));}

        return interfaceEmployeeDao.addNewEmployee(employee);
    }

    @Transactional
    public void addNewAddress(AddressDomain addressDomain, Person person) {
        Address address = new Address();
        address.setAddressLine1(addressDomain.getAddressLine1());
        address.setAddressLine2(addressDomain.getAddressLine2());
        address.setCity(addressDomain.getCity());
        address.setZipcode(addressDomain.getZipcode());
        address.setStateName(addressDomain.getStateName());
        address.setStateAbbr(addressDomain.getStateAbbr());
        address.setPerson(person);
        address.setPersonId(person.getId());

        interfaceAddressDao.addNewAddress(address);
    }

    @Transactional
    public void addNewContact(ContactDomain contactDomain, Person person) {
        Contact contact = new Contact();
        contact.setPerson(person);
        contact.setFullName(contactDomain.getFullName());
        contact.setPhone(contactDomain.getPhone());
        contact.setRelationship(contactDomain.getRelationship());
        contact.setTitle(contactDomain.getTitle());
        contact.setAddress(contactDomain.getAddress());
        contact.setIsReference(contactDomain.getIsReference());
        contact.setIsEmergency(contactDomain.getIsEmergency());
        contact.setIsLandlord(contactDomain.getIsLandlord());
        contact.setPersonId(person.getId());

        interfaceContactDao.addNewContact(contact);
    }
}
