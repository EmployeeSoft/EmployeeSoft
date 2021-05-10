package dao;

import java.sql.Date;

public interface InterfaceVisaStatusDao {
    // Get the visa status ID, by type
    int getIdByType(String visaType);

    // Get the visa type by ID
    String getVisaTypeByID(Integer id);

    // Given the visa status ID, notify user if it is active
    boolean getIsActiveById(Integer id);

    // Given the visa status ID, get the date last modified
    Date getDateModifiedById(Integer id);

    // Given the visa status ID, get the create user ?
    String getCreateUserById(Integer id);
}
