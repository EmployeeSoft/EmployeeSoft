package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfaceUserRoleDao;
import com.example.demo.entity.UserRole;

import java.sql.Date;

public class UserRoleDao implements InterfaceUserRoleDao {
    // Given the user role ID, get the user role
    public UserRole getUserRoleById(Integer id) {
        // TODO
        return new UserRole();
    }

    // Given the user ID, get the user role
    public UserRole getUserRoleByUserId(Integer userId) {
        // TODO
        return new UserRole();
    }

    // Get the user role ID from the user ID
    public int getUserRoleIdByUserId(Integer userId) {
        // TODO
        return 0;
    }

    // Given the user role ID, get the role ID
    public int getRoleIdById(Integer id) {
        // TODO
        return 0;
    }

    // Given the user role ID, get the active flag
    public boolean getActiveFlagById(Integer id) {
        // TODO
        return false;
    }

    // Given the user role ID, get the date created
    public Date getDateCreatedById(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the user role ID, get the date last modified
    public Date getDateModifiedById(Integer id) {
        // TODO
        return new Date(1234567890);
    }
}
