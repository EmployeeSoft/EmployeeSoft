package dao.implementation;

import dao.InterfaceDigitalDocDao;
import entity.DigitalDocument;

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
}
