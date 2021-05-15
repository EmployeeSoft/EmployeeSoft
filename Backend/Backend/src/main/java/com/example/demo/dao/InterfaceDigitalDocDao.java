package com.example.demo.dao;

import com.example.demo.entity.DigitalDocument;

import java.util.List;

public interface InterfaceDigitalDocDao {
    // After user completes the application form, the documentation page should be loaded with all documents
    List<DigitalDocument> getAddDigitalDocuments();

    // All required documents should be validated before user can submit the application
    List<DigitalDocument> getRequiredDocuments();

    // Get the digital Document, return the path
    String getDigitalDocument(String filename);
}
