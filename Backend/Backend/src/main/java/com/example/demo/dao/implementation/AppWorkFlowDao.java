package com.example.demo.dao.implementation;

import com.example.demo.config.HibernateConfig;
import com.example.demo.dao.InterfaceAppWorkFlowDao;
import com.example.demo.entity.Address;
import com.example.demo.entity.ApplicationWorkFlow;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class AppWorkFlowDao extends AbstractHibernateDao<ApplicationWorkFlow> implements InterfaceAppWorkFlowDao {
    public AppWorkFlowDao() { setClazz(ApplicationWorkFlow.class); }

    // Given the employee ID, get the ID of that employee Application WorkFlow
    public int getIdByEmployeeId(Integer employeeId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT id FROM ApplicationWorkFlow WHERE employeeId = :employeeId");
        query.setParameter("employeeId", employeeId);
        int id = (int) query.uniqueResult();
        return id;
    }

    // Given the Application WorkFlow ID, get the Employee ID that it belongs to
    public int getEmployeeIdById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT employeeId FROM ApplicationWorkFlow WHERE id = :id");
        query.setParameter("id", id);
        int eId = (int) query.uniqueResult();
        return eId;
    }

    // Given the Application WorkFlow ID, get the date it was created
    public Date getDateCreatedById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT dateCreated FROM ApplicationWorkFlow WHERE id = :id");
        query.setParameter("id", id);
        Date date = (Date) query.uniqueResult();
        return date;
    }

    // Given the Application WorkFlow ID, get the date it was modified
    public Date getDateModifiedById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT dateModified FROM ApplicationWorkFlow WHERE id = :id");
        query.setParameter("id", id);
        Date date = (Date) query.uniqueResult();
        return date;
    }

    // Given the Application WorkFlow ID, get the status of the application
    public boolean getStatusById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT status FROM ApplicationWorkFlow WHERE id = :id");
        query.setParameter("id", id);
        int status = (int) query.uniqueResult();
        return status == 1;
    }

    // Given the Application WorkFlow Id, get the comment for that ID
    public String getCommentByID(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT comment FROM ApplicationWorkFlow WHERE id = :id");
        query.setParameter("id", id);
        String comment = (String) query.uniqueResult();
        return comment;
    }

    // Given the employee ID, get the comment that was intended for that employee from the HR
    public String getCommentByEmployeeId(Integer employeeId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT comment FROM ApplicationWorkFlow WHERE employeeId = :employeeId");
        query.setParameter("employeeId", employeeId);
        String comment = (String) query.uniqueResult();
        return comment;
    }

    // Given the Application WorkFlow ID, get the application type
    public String getTypeById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT type FROM ApplicationWorkFlow WHERE id = :id");
        query.setParameter("id", id);
        String type = (String) query.uniqueResult();
        return type;
    }


    ///// REQUIRED METHODS BELOW /////


    // Given the Application WorkFlow ID, get the application's information
    public ApplicationWorkFlow getApplicationWorkFlowById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM ApplicationWorkFlow WHERE id = :id");
        query.setParameter("id", id);
        System.out.println(query.list());
        ApplicationWorkFlow applicationWorkFlow = (ApplicationWorkFlow) query.uniqueResult();
        return applicationWorkFlow;
    }
}
