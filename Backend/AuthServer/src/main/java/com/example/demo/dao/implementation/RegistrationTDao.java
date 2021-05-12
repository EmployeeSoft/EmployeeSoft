package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfaceRegistrationTDao;
import com.example.demo.entity.RegistrationToken;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.sql.Date;
import java.util.List;

@Repository
public class RegistrationTDao extends AbstractHibernateDao<RegistrationToken> implements InterfaceRegistrationTDao {
    public RegistrationTDao() {setClazz(RegistrationToken.class);}

    // Given the token, get the ID
    public int getIdByToken(String token) {
        // TODO
        return 0;
    }

    // Get the token string by providing the ID
    public String getTokenById(Integer id) {
        // TODO
        return "";
    }

    // Given the ID, get the token start time (When was the token created)
    public Date getStartTimeById(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the ID, get the token end time (endTime - startTime = token duration)
    public Date getEndTimeById(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the ID, get the email that is ass
    public String getEmailById(Integer id) {
        // TODO
        return "";
    }

    // Get the HR member who created the token
    public String getCreatedByById(Integer id) {
        // TODO
        return "";
    }

    // Given the ID, get the registration token information
    public RegistrationToken getRegistrationTokenById(Integer id) {
        RegistrationToken registrationToken = new RegistrationToken();
        registrationToken = getCurrentSession().get(RegistrationToken.class, 1);
        return registrationToken;
    }

    @Override
    public RegistrationToken getRegistrationTokenByEmail(String email) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM RegistrationToken rt WHERE rt.email = :email");
        query.setParameter("email", email);
        RegistrationToken registrationToken = (RegistrationToken) query.uniqueResult();
        return registrationToken;
//        RegistrationToken registrationToken;
//        try {
//            registrationToken = (RegistrationToken) query.getSingleResult();
//            return registrationToken;
//        } catch (NoResultException e) {
//            return null;
//        }
    }

    @Override
    public void createNewRegistrationToken(RegistrationToken registrationToken) {
        merge(registrationToken);
    }

    @Override
    public List<RegistrationToken> getRegistrationTokenByToken(String token) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM RegistrationToken rt WHERE rt.token = :token");
        query.setParameter("token", token);
        return (List<RegistrationToken>) query.getResultList();
    }
}
