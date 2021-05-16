package com.example.demo.service;

import com.example.demo.dao.InterfacePersonDao;
import com.example.demo.dao.implementation.PersonDao;
import com.example.demo.domain.*;
import com.example.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class PersonService {
    private InterfacePersonDao personDao;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ContactService contactService;

    @Autowired
    public void setInterfacePersonDao(InterfacePersonDao personDao) {
        this.personDao = personDao;
    }

    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    public PersonDomain getPersonById(Integer id) {
        Person person = personDao.getPersonById(id);

        PersonDomain personDomain = PersonDomain.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .middleName(person.getMiddleName())
                .preferName(person.getPreferName())
                .cellPhone(person.getCellPhone())
                .altPhone(person.getAltPhone())
                .gender(person.getGender())
                .ssn(person.getSsn())
                .dob(df.format(person.getDob()))
                .build();

        return personDomain;
    }


    @Transactional
    public String getFirstNameById(Integer id) {
        return personDao.getFirstNameById(id);
    }

    @Transactional
    public String getLastNameById(Integer id) {
        return personDao.getLastNameById(id);
    }

    @Transactional
    String getMiddleNameById(Integer id) {
        return personDao.getMiddleNameById(id);
    }

    @Transactional
    public String getEmailById(Integer id) {
        return personDao.getEmailById(id);
    }

    @Transactional
    public String getCellPhoneById(Integer id) {
        return personDao.getCellPhoneById(id);
    }

    @Transactional
    public String getAltPhoneById(Integer id) {
        return personDao.getAltPhoneById(id);
    }

    @Transactional
    public String getGenderById(Integer id) {
        return personDao.getGenderById(id);
    }

    @Transactional
    public String getLastFourDigitSSNById(Integer id) {
        return personDao.getLastFourDigitSSNById(id);
    }

    @Transactional
    public String getDobById(Integer id) {
        return df.format(personDao.getDobById(id));
    }

    @Transactional
    public PersonDomain getPersonByUserId(Integer userId) {
        Person person = personDao.getPersonByUserId(userId);

        PersonDomain personDomain = PersonDomain.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .middleName(person.getMiddleName())
                .preferName(person.getPreferName())
                .cellPhone(person.getCellPhone())
                .altPhone(person.getAltPhone())
                .email(person.getEmail())
                .gender(person.getGender())
                .ssn(person.getSsn())
                .dob(df.format(person.getDob()))
                .addressDomains(addressService.getAddressListByPersonId(person.getId()))
                .contactDomains(contactService.getContactListByPersonId(person.getId()))
                .build();

        return personDomain;
    }

    @Transactional
    public String getGenderByUserId(Integer userId) {
        return personDao.getGenderByUserId(userId);
    }

    @Transactional
    public String getLastFourDigitSSNByUserId(Integer userId) {
        return personDao.getLastFourDigitSSNByUserId(userId);
    }

    @Transactional
    public int getPersonIdByUserId(Integer userId) {
        return personDao.getPersonIdByUserId(userId);
    }

    @Transactional
    public String getFirstNameByUserId(Integer userId) {
        return personDao.getFirstNameByUserId(userId);
    }

    @Transactional
    public String getMiddleNameByUserId(Integer userId) {
        return personDao.getMiddleNameByUserId(userId);
    }

    @Transactional
    public String getLastNameByUserId(Integer userId) {
        return personDao.getLastNameByUserId(userId);
    }

    @Transactional
    public String getEmailByUserId(Integer userId) {
        return personDao.getEmailByUserId(userId);
    }

    @Transactional
    public String getCellPhoneByUserId(Integer userId) {
        return personDao.getCellPhoneByUserId(userId);
    }

    @Transactional
    public String getAltPhoneByUserId(Integer userId) {
        return personDao.getAltPhoneByUserId(userId);
    }

    @Transactional
    public String getPreferNameByUserId(Integer userId) {
        return personDao.getPreferNameByUserId(userId);
    }

    @Transactional
    public String getDobByUserId(Integer userId) {
        return df.format(personDao.getDobByUserId(userId));
    }

    @Transactional
    public int getUserIdByPersonId(Integer personId) {
        return personDao.getUserIdByPersonId(personId);
    }

    @Transactional
    public boolean updatePersonInfo(PersonDomain personDomain){
        return personDao.updatePersonInfo(personDomain);
    }
}
