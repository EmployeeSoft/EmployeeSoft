package com.example.demo.service;

import com.example.demo.dao.InterfaceUserDao;
import com.example.demo.dao.InterfaceUserRoleDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private InterfaceUserDao interfaceUserDao;
    private InterfaceUserRoleDao interfaceUserRoleDao;

    @Autowired
    public void setInterfaceUserRoleDao(InterfaceUserRoleDao interfaceUserRoleDao) {
        this.interfaceUserRoleDao = interfaceUserRoleDao;
    }

    @Autowired
    public void setInterfaceUserDao(InterfaceUserDao interfaceUserDao) {
        this.interfaceUserDao = interfaceUserDao;
    }

    @Transactional
    public User validate(String username, String pwd) {
        User user = interfaceUserDao.getUserByUsername(username);
        if (user == null || !user.getPwd().equals(pwd)) {
            return null;
        }
        return user;
    }

    @Transactional
    public Role getRoleByUser(User user) {
        Role role = interfaceUserRoleDao.getRoleByUser(user);
        if (role == null) {
            System.out.println("null");
        } else {
            System.out.println(role.getRoleName());
        }
        return role;
    }
}
