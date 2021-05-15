package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfaceDigitalDocDao;
import com.example.demo.entity.DigitalDocument;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DigitalDocDao extends AbstractHibernateDao<DigitalDocument> implements InterfaceDigitalDocDao {
    public DigitalDocDao() { setClazz(DigitalDocument.class); }

    // After user completes the application form, the documentation page should be loaded with all documents
    public List<DigitalDocument> getAddDigitalDocuments() {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM DigitalDocument");
        List<DigitalDocument> digitalDocuments = query.list();
        return digitalDocuments;
    }

    // All required documents should be validated before user can submit the application
    public List<DigitalDocument> getRequiredDocuments() {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM DigitalDocument WHERE required = true");
        List<DigitalDocument> digitalDocuments = query.list();
        return digitalDocuments;
    }

    // Get the digital Document return the path
    public String getDigitalDocument(String filename) {
        /*
            filename can either be "I-983 Form" or "I-983 Sample"
        */

        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT templateLocation FROM DigitalDocument WHERE type = :filename");
        query.setParameter("filename", filename);
        return (String) query.uniqueResult();
    }
}
