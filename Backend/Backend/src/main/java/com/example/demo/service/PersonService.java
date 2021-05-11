package com.example.demo.service;

import com.example.demo.dao.InterfacePersonDao;
import com.example.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class PersonService {
    private InterfacePersonDao interfacePersonDao;

    @Autowired
    public void setInterfacePersonDao(InterfacePersonDao interfacePersonDao) {
        this.interfacePersonDao = interfacePersonDao;
    }

    @Transactional
    public Person getPersonById(Integer id) {
        return interfacePersonDao.getPersonById(id);
    }

    @Transactional
    public Person getPersonBySSN(String ssn) {
        return interfacePersonDao.getPersonBySSN(ssn);
    }

    @Transactional
    public Person getPersonByInfo(String firstName, String lastName, Date dob) {
        return getPersonByInfo(firstName, lastName, dob);
    }
}
