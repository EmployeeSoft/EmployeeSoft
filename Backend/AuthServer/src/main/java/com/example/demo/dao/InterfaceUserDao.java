package com.example.demo.dao;

import com.example.demo.entity.User;

import java.sql.Date;
import java.util.List;

public interface InterfaceUserDao {
    // Given the ID, get the user information
    User getUserById(Integer id);

    // Given the user's username, get the user's information
    User getUserByUsername(String username);

    // Given the user's email, get the user's information
    User getUserByEmail(String email);

    // Given the ID, get the user's username
    String getUsernameById(Integer id);

    // Given the ID, get the user's email
    String getEmailById(Integer id);

    // Given the user's username, get the email
    String getEmailByUsername(String username);

    // Given the ID, get the date created
    Date getDateCreatedById(Integer id);

    // Given the ID, get the date modified
    Date getDateModifiedById(Integer id);

    User createUser(User newUser);
}
