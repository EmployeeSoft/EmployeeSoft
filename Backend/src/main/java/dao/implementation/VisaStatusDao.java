package dao.implementation;

import dao.InterfaceVisaStatusDao;
import entity.VisaStatus;

import java.sql.Date;

public class VisaStatusDao implements InterfaceVisaStatusDao {
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

    // Given the visa status ID, get the create user ?
    public String getCreateUserById(Integer id) {
        // TODO
        return "";
    }

    // Given the ID, get the visa status information
    public VisaStatus getVisaStatusById(Integer id) {
        // TODO
        return new VisaStatus();
    }
}
