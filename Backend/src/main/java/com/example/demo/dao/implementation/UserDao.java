package com.example.demo.dao.implementation;

import com.example.demo.config.HibernateConfig;
import com.example.demo.dao.InterfaceUserDao;
import com.example.demo.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;

public class UserDao implements InterfaceUserDao {
    // Given the ID, get the user information
    // Used in Authentication server instead
    public User getUserById(Integer id) {
        // TODO
        return new User();
    }

    // Given the user's username, get the user's information
    public User getUserByUsername(String username) {
        // TODO
        return new User();
    }

    // Given the user's email, get the user's information
    public User getUserByEmail(String email) {
        // TODO
        return new User();
    }

    // Given the ID, get the user's username
    public String getUsernameById(Integer id) {
        // TODO
        return "";
    }

    // Given the ID, get the user's email
    public String getEmailById(Integer id) {
        // TODO
        return "";
    }

    // Given the user's username, get the email
    public String getEmailByUsername(String username) {
        // TODO
        return "";
    }

    // Given the ID, get the date created
    public Date getDateCreatedById(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the ID, get the date modified
    public Date getDateModifiedById(Integer id) {
        // TODO
        return new Date(1234567890);
    }
}
