package com.example.demo.dao;

import com.example.demo.entity.DigitalDocument;

import java.util.List;

public interface InterfaceDigitalDocDao {
    // Given the Digital Document ID, return the document information
    DigitalDocument getDigitalDocumentById(Integer id);

    // Get the document type by using the Document ID
    String getTypeById(Integer id);

    // Given the Document ID, inform user if the document is required or not
    boolean getRequiredById(Integer id);

    // Given the Document ID, get the template location
    String getTemplateLocationById(Integer id);

    // Given the Document ID, get the description of the document
    String getDescriptionById(Integer id);

    // Given the document type, inform the user if that document type is required
    boolean getRequiredByType(String docType);

    // Given the document type, inform the user of the document description
    String getDescriptionByType(String docType);

    // Given the document type, get the template location
    String getTemplateLocationByType(String docType);


    ///// REQUIRED METHODS BELOW /////


    // After user completes the application form, the documentation page should be loaded with all documents
    List<DigitalDocument> getAddDigitalDocuments();

    // All required documents should be validated before user can submit the application
    List<DigitalDocument> getRequiredDocuments();
}
