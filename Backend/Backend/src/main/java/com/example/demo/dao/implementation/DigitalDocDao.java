package com.example.demo.dao.implementation;

import com.example.demo.config.HibernateConfig;
import com.example.demo.dao.InterfaceDigitalDocDao;
import com.example.demo.entity.DigitalDocument;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DigitalDocDao implements InterfaceDigitalDocDao {
    // Given the Digital Document ID, return the document information
    public DigitalDocument getDigitalDocumentById(Integer id) {
        // TODO
        return new DigitalDocument();
    }

    // Get the document type by using the Document ID
    public String getTypeById(Integer id) {
        // TODO
        return "";
    }

    // Given the Document ID, inform user if the document is required or not
    public boolean getRequiredById(Integer id) {
        // TODO
        return false;
    }

    // Given the Document ID, get the template location
    public String getTemplateLocationById(Integer id) {
        // TODO
        return "";
    }

    // Given the Document ID, get the description of the document
    public String getDescriptionById(Integer id) {
        // TODO
        return "";
    }

    // Given the document type, inform the user if that document type is required
    public boolean getRequiredByType(String docType) {
        // TODO
        return false;
    }

    // Given the document type, inform the user of the document description
    public String getDescriptionByType(String docType) {
        // TODO
        return "";
    }

    // Given the document type, get the template location
    public String getTemplateLocationByType(String docType) {
        // TODO
        return "";
    }


    ///// REQUIRED METHODS BELOW /////


    // After user completes the application form, the documentation page should be loaded with all documents
    public List<DigitalDocument> getAddDigitalDocuments() {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM DigitalDocument");
            List<DigitalDocument> digitalDocuments = query.list();
            transaction.commit();
            return digitalDocuments;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }

    // All required documents should be validated before user can submit the application
    public List<DigitalDocument> getRequiredDocuments() {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM DigitalDocument WHERE required = true");
            List<DigitalDocument> digitalDocuments = query.list();
            transaction.commit();
            return digitalDocuments;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return null;
    }
}
