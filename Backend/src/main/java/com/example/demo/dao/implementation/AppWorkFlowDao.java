package com.example.demo.dao.implementation;

import com.example.demo.config.HibernateConfig;
import com.example.demo.dao.InterfaceAppWorkFlowDao;
import com.example.demo.entity.ApplicationWorkFlow;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;

public class AppWorkFlowDao implements InterfaceAppWorkFlowDao {
    // Given the employee ID, get the ID of that employee Application WorkFlow
    public int getIdByEmployeeId(Integer employeeId) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT id FROM ApplicationWorkFlow WHERE employeeId = :employeeId");
            query.setParameter("employeeId", employeeId);
            int id = (int) query.uniqueResult();
            transaction.commit();
            return id;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return -1;
    }

    // Given the Application WorkFlow ID, get the Employee ID that it belongs to
    public int getEmployeeIdById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT employeeId FROM ApplicationWorkFlow WHERE id = :id");
            query.setParameter("id", id);
            int eId = (int) query.uniqueResult();
            transaction.commit();
            return eId;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return -1;
    }

    // Given the Application WorkFlow ID, get the date it was created
    public Date getDateCreatedById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT dateCreated FROM ApplicationWorkFlow WHERE id = :id");
            query.setParameter("id", id);
            Date date = (Date) query.uniqueResult();
            transaction.commit();
            return date;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Given the Application WorkFlow ID, get the date it was modified
    public Date getDateModifiedById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT dateModified FROM ApplicationWorkFlow WHERE id = :id");
            query.setParameter("id", id);
            Date date = (Date) query.uniqueResult();
            transaction.commit();
            return date;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Given the Application WorkFlow ID, get the status of the application
    public boolean getStatusById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT status FROM ApplicationWorkFlow WHERE id = :id");
            query.setParameter("id", id);
            int status = (int) query.uniqueResult();
            transaction.commit();
            return status == 1;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return false;
    }

    // Given the Application WorkFlow Id, get the comment for that ID
    public String getCommentByID(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT comment FROM ApplicationWorkFlow WHERE id = :id");
            query.setParameter("id", id);
            String comment = (String) query.uniqueResult();
            transaction.commit();
            return comment;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Given the employee ID, get the comment that was intended for that employee from the HR
    public String getCommentByEmployeeId(Integer employeeId) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT comment FROM ApplicationWorkFlow WHERE employeeId = :employeeId");
            query.setParameter("employeeId", employeeId);
            String comment = (String) query.uniqueResult();
            transaction.commit();
            return comment;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Given the Application WorkFlow ID, get the application type
    public String getTypeById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT type FROM ApplicationWorkFlow WHERE id = :id");
            query.setParameter("id", id);
            String type = (String) query.uniqueResult();
            transaction.commit();
            return type;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // Given the Application WorkFlow ID, get the application's information
    public ApplicationWorkFlow getApplicationWorkFlowById(Integer id) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM ApplicationWorkFlow WHERE id = :id");
            query.setParameter("id", id);
            System.out.println(query.list());
            ApplicationWorkFlow applicationWorkFlow = (ApplicationWorkFlow) query.uniqueResult();
            transaction.commit();
            return applicationWorkFlow;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }
}
