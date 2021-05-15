package com.example.demo.service;

import com.example.demo.dao.InterfaceRegistrationTDao;
import com.example.demo.dao.InterfaceRoleDao;
import com.example.demo.dao.InterfaceUserDao;
import com.example.demo.dao.InterfaceUserRoleDao;
import com.example.demo.entity.RegistrationToken;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class RegistrationTokenService {
    private static final int VALID_TIME_IN_MILLIS = 24 * 60 * 60 * 1000;
    private static final int EMPLOYEE_ROLE_ID = 2;

    private InterfaceRegistrationTDao interfaceRegistrationTDao;
    private InterfaceUserDao interfaceUserDao;
    private InterfaceRoleDao interfaceRoleDao;
    private InterfaceUserRoleDao interfaceUserRoleDao;

    @Autowired
    public void setInterfaceRoleDao(InterfaceRoleDao interfaceRoleDao) {
        this.interfaceRoleDao = interfaceRoleDao;
    }

    @Autowired
    public void setInterfaceUserRoleDao(InterfaceUserRoleDao interfaceUserRoleDao) {
        this.interfaceUserRoleDao = interfaceUserRoleDao;
    }

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
        RegistrationToken registrationToken = new RegistrationToken();
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
    public int createUser(String username, String pwd, String email) {
        User user = interfaceUserDao.getUserByUsername(username);
        if (user != null) { //duplicated username
            return 0;
        }
        user = new User();
        user.setUsername(username);
        user.setPwd(pwd);
        user.setEmail(email);
        user.setDateCreated(new Date(System.currentTimeMillis()));
        user.setDateModified(new Date(System.currentTimeMillis()));
        user = interfaceUserDao.createUser(user);

        Role role = interfaceRoleDao.getRoleById(EMPLOYEE_ROLE_ID);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setDateCreated(new Date(System.currentTimeMillis()));
        userRole.setDateModified(new Date(System.currentTimeMillis()));
        userRole.setActiveFlag(true);
        userRole.setLastModifiedUser("Elliot");

        interfaceUserRoleDao.createUserRole(userRole);
        return user.getId();
    }
}
