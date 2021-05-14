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
import java.util.ArrayList;

@Repository
public class AppWorkFlowDao extends AbstractHibernateDao<ApplicationWorkFlow> implements InterfaceAppWorkFlowDao {
    public AppWorkFlowDao() { setClazz(ApplicationWorkFlow.class); }

    // Given the Application WorkFlow ID, get the application's information
    public ApplicationWorkFlow getApplicationWorkFlowById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM ApplicationWorkFlow WHERE id = :id");
        query.setParameter("id", id);
        System.out.println(query.list());
        ApplicationWorkFlow applicationWorkFlow = (ApplicationWorkFlow) query.uniqueResult();
        return applicationWorkFlow;
    }

    // Get a list of all application work flow by employee id
    public ArrayList<ApplicationWorkFlow> getAppWorkFlowsByEmployeeId(Integer employeeId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM ApplicationWorkFlow ");
        return (ArrayList<ApplicationWorkFlow>) query.list();
    }
}
