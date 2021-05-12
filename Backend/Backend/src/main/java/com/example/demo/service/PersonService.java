package com.example.demo.service;

import com.example.demo.dao.InterfacePersonDao;
import com.example.demo.domain.PersonDomain;
import com.example.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class PersonService {
    private InterfacePersonDao personDao;

    @Autowired
    public void setInterfacePersonDao(InterfacePersonDao personDao) {
        this.personDao = personDao;
    }

    @Transactional
    public PersonDomain getPersonById(Integer id) {
        Person person = personDao.getPersonById(id);
        PersonDomain personDomain = PersonDomain.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .middleName(person.getMiddleName())
                .cellPhone(person.getCellPhone())
                .altPhone(person.getAltPhone())
                .gender(person.getGender())
                .ssn(person.getSsn())
                .dob(person.getDob())
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
    public Date getDobById(Integer id) {
        return personDao.getDobById(id);
    }

    @Transactional
    public PersonDomain getPersonByUserId(Integer userId) {
        Person person = personDao.getPersonByUserId(userId);
        PersonDomain personDomain = PersonDomain.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .middleName(person.getMiddleName())
                .cellPhone(person.getCellPhone())
                .altPhone(person.getAltPhone())
                .gender(person.getGender())
                .ssn(person.getSsn())
                .dob(person.getDob())
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
}
