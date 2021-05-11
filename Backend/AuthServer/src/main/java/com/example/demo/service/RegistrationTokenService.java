package com.example.demo.service;

import com.example.demo.dao.InterfaceRegistrationTDao;
import com.example.demo.entity.RegistrationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class RegistrationTokenService {
    private static final int VALID_TIME_IN_MILLIS = 24 * 60 * 60 * 1000;
    private InterfaceRegistrationTDao interfaceRegistrationTDao;

    @Autowired
    public void setInterfaceRegistrationTDao(InterfaceRegistrationTDao interfaceRegistrationTDao) {
        this.interfaceRegistrationTDao = interfaceRegistrationTDao;
    }

    @Transactional
    public boolean createRegistrationToken(String token, String email) {
        RegistrationToken registrationToken = interfaceRegistrationTDao.getRegistrationTokenByEmail(email);
        if (registrationToken == null) {
            return false;
        }
        registrationToken.setToken(token);
        registrationToken.setEmail(email);
        registrationToken.setStartTime(new Date(System.currentTimeMillis()));
        registrationToken.setEndTime(new Date(System.currentTimeMillis() + VALID_TIME_IN_MILLIS));
        interfaceRegistrationTDao.createNewRegistrationToken(registrationToken);
        return true;
    }
}
