package com.example.demo.service;

import com.example.demo.dao.InterfaceRegistrationTDao;
import com.example.demo.dao.InterfaceUserDao;
import com.example.demo.entity.RegistrationToken;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class RegistrationTokenService {
    private static final int VALID_TIME_IN_MILLIS = 24 * 60 * 60 * 1000;
    private InterfaceRegistrationTDao interfaceRegistrationTDao;
    private InterfaceUserDao interfaceUserDao;

    @Autowired
    public void setInterfaceRegistrationTDao(InterfaceRegistrationTDao interfaceRegistrationTDao) {
        this.interfaceRegistrationTDao = interfaceRegistrationTDao;
    }

    @Autowired
    public void setInterfaceUserDao(InterfaceUserDao interfaceUserDao) {
        this.interfaceUserDao = interfaceUserDao;
    }

    @Transactional
    public boolean createRegistrationToken(String token, String email, String createdBy) {
        System.out.println(email);
        System.out.println(createdBy);
//        RegistrationToken test = interfaceRegistrationTDao.getRegistrationTokenById(1);
//        System.out.println(test.getEmail());
        RegistrationToken registrationToken = interfaceRegistrationTDao.getRegistrationTokenByEmail(email);
//        System.out.println(registrationToken.isEmpty());
        if (registrationToken != null) {
            return false;
        }
        registrationToken = new RegistrationToken();
        registrationToken.setToken(token);
        registrationToken.setEmail(email);
        registrationToken.setStartTime(new Date(System.currentTimeMillis()));
        registrationToken.setEndTime(new Date(System.currentTimeMillis() + VALID_TIME_IN_MILLIS));
        registrationToken.setCreatedBy(createdBy);
        interfaceRegistrationTDao.createNewRegistrationToken(registrationToken);
        return true;
    }

    @Transactional
    public String getEmailFromToken(String token) {
        List<RegistrationToken> registrationTokens = interfaceRegistrationTDao.getRegistrationTokenByToken(token);
        String email = "";
        if (registrationTokens.isEmpty()) {//No token found
            return "error";
        }
        Date currentTime = new Date(System.currentTimeMillis());
        Date expTime = registrationTokens.get(0).getEndTime();
        if (expTime.getTime() - currentTime.getTime() <= 0) {//Expired token
            return "error";
        }
        email = registrationTokens.get(0).getEmail();
        return email;
    }

    @Transactional
    public boolean createUser(String username, String pwd, String email) {
        List<User> users = interfaceUserDao.getUserByUsername(username);
        if (!users.isEmpty()) {
            return false;
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPwd(pwd);
        newUser.setEmail(email);
        newUser.setDateCreated(new Date(System.currentTimeMillis()));
        newUser.setDateModified(new Date(System.currentTimeMillis()));
        interfaceUserDao.createUser(newUser);
        return true;
    }
}
