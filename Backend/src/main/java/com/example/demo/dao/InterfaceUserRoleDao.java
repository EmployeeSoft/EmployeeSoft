package com.example.demo.dao;

import com.example.demo.entity.UserRole;

import java.sql.Date;

public interface InterfaceUserRoleDao {
    // Given the user role ID, get the user role
    UserRole getUserRoleById(Integer id);

    // Given the user ID, get the user role
    UserRole getUserRoleByUserId(Integer userId);

    // Get the user role ID from the user ID
    int getUserRoleIdByUserId(Integer userId);

    // Given the user role ID, get the role ID
    int getRoleIdById(Integer id);

    // Given the user role ID, get the active flag
    boolean getActiveFlagById(Integer id);

    // Given the user role ID, get the date created
    Date getDateCreatedById(Integer id);

    // Given the user role ID, get the date last modified
    Date getDateModifiedById(Integer id);
}
