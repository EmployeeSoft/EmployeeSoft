package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfaceRegistrationTDao;
import com.example.demo.entity.RegistrationToken;
<<<<<<< HEAD:Backend/AuthServer/src/main/java/com/example/demo/dao/implementation/RegistrationTDao.java
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
=======
>>>>>>> eb9e0aaf826cf8645d51b999e902ffc00040a823:Backend/AuthServer/src/main/java/dao/implementation/RegistrationTDao.java

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
        // TODO
        return new RegistrationToken();
    }

    @Override
    public List<RegistrationToken> getRegistrationTokenByEmail(String email) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM RegistrationToken rt WHERE rt.email = :email");
        query.setParameter("email", email);
        return (List<RegistrationToken>) query.getResultList();
    }

    @Override
    public void createNewRegistrationToken(RegistrationToken registrationToken) {
        merge(registrationToken);
    }
}
