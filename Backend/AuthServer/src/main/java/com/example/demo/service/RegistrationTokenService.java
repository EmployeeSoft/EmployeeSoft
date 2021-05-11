package com.example.demo.service;

import com.example.demo.dao.InterfaceRegistrationTDao;
import com.example.demo.entity.RegistrationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class RegistrationTokenService {
    private static final int VALID_TIME_IN_MILLIS = 24 * 60 * 60 * 1000;
    private InterfaceRegistrationTDao interfaceRegistrationTDao;

    @Autowired
    public void setInterfaceRegistrationTDao(InterfaceRegistrationTDao interfaceRegistrationTDao) {
        this.interfaceRegistrationTDao = interfaceRegistrationTDao;
    }

    @Transactional
    public boolean createRegistrationToken(String token, String email, String createdBy) {
        List<RegistrationToken> registrationTokens = interfaceRegistrationTDao.getRegistrationTokenByEmail(email);
//        System.out.println(registrationTokens.isEmpty());
        if (!registrationTokens.isEmpty()) {
            return false;
        }
        RegistrationToken registrationToken = new RegistrationToken();
        registrationToken.setToken(token);
        registrationToken.setEmail(email);
        registrationToken.setStartTime(new Date(System.currentTimeMillis()));
        registrationToken.setEndTime(new Date(System.currentTimeMillis() + VALID_TIME_IN_MILLIS));
        registrationToken.setCreatedBy(createdBy);
        interfaceRegistrationTDao.createNewRegistrationToken(registrationToken);
        return true;
    }
}
