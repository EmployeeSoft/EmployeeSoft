package com.example.demo.service;

import com.example.demo.dao.InterfacePersonalDocDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalDocService {
    private InterfacePersonalDocDao personalDocDao;

    @Autowired
    public void setPersonalDocDao(InterfacePersonalDocDao personalDocDao) {
        this.personalDocDao = personalDocDao;
    }


}
