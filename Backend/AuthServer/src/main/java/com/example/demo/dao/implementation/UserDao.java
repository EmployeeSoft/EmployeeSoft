package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfaceUserDao;
import com.example.demo.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class UserDao extends AbstractHibernateDao<User> implements InterfaceUserDao {
    public UserDao() {setClazz(User.class);}

    // Given the ID, get the user information
    // Used in Authentication server instead
    public User getUserById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User WHERE id = :id");
            query.setParameter("id", id);
            User user = (User) query.uniqueResult();
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }


    public User getUserByUsername(String username) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM User u WHERE u.username = :username");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
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

    @Override
    public User createUser(User newUser) {
        return merge(newUser);
    }
}
