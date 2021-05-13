package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfaceVisaStatusDao;
import com.example.demo.entity.VisaStatus;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class VisaStatusDao extends AbstractHibernateDao<VisaStatus> implements InterfaceVisaStatusDao {
    public VisaStatusDao() { setClazz(VisaStatus.class); }

    // Get the visa status ID, by type
    public int getIdByType(String visaType) {
        // TODO
        return 0;
    }

    // Get the visa type by ID
    public String getVisaTypeByID(Integer id) {
        // TODO
        return "";
    }

    // Given the visa status ID, notify user if it is active
    public boolean getIsActiveById(Integer id) {
        // TODO
        return false;
    }

    // Given the visa status ID, get the date last modified
    public Date getDateModifiedById(Integer id) {
        // TODO
        return new Date(1234567890);
    }


    ///// REQUIRED METHODS BELOW /////


    // Given the ID, get the visa status information
    public VisaStatus getVisaStatusById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM VisaStatus WHERE id = :id");
        query.setParameter("id", id);
        VisaStatus visaStatus = (VisaStatus) query.uniqueResult();
        return visaStatus;
    }

    // Visa Status Management is only available if the user is NOT a citizen or a green card holder
    public Boolean visaStatusManagementAble(String createUser) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT visaType FROM VisaStatus WHERE createUser = :createUser");
        query.setParameter("createUser", createUser);
        String visaType = (String) query.uniqueResult();

        if (visaType.equals("U.S. Citizen") || visaType.equals("Green Card")) {
            return false;
        } else {
            return true;
        }
    }

    // Given the visa status ID, get the create user ?
    public String getCreateUserById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT createUser FROM VisaStatus WHERE id = :id");
        query.setParameter("id", id);
        String createUser = (String) query.uniqueResult();
        return createUser;
    }
}
