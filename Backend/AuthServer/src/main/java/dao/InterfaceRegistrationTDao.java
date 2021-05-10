package dao;

import entity.RegistrationToken;

import java.sql.Date;

public interface InterfaceRegistrationTDao {
    // Given the token, get the ID
    int getIdByToken(String token);

    // Get the token string by providing the ID
    String getTokenById(Integer id);

    // Given the ID, get the token start time (When was the token created)
    Date getStartTimeById(Integer id);

    // Given the ID, get the token end time (endTime - startTime = token duration)
    Date getEndTimeById(Integer id);

    // Given the ID, get the email that is ass
    String getEmailById(Integer id);

    // Get the HR member who created the token
    String getCreatedByById(Integer id);

    // Given the ID, get the registration token information
    RegistrationToken getRegistrationTokenById(Integer id);
}
