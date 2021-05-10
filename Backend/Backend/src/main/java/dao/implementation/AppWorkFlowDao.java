package dao.implementation;

import dao.InterfaceAppWorkFlowDao;
import entity.ApplicationWorkFlow;

import java.sql.Date;

public class AppWorkFlowDao implements InterfaceAppWorkFlowDao {
    // Given the employee ID, get the ID of that employee Application WorkFlow
    public int getIdByEmployeeId(Integer employeeId) {
        // TODO
        return 0;
    }

    // Given the Application WorkFlow ID, get the Employee ID that it belongs to
    public int getEmployeeIdById(Integer id) {
        // TODO
        return 0;
    }

    // Given the Application WorkFlow ID, get the date it was created
    public Date getDateCreatedById(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the Application WorkFlow ID, get the date it was modified
    public Date getDateModifiedById(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the Application WorkFlow ID, get the status of the application
    public boolean getStatusById(Integer id) {
        // TODO
        return false;
    }

    // Given the Application WorkFlow Id, get the comment for that ID
    public String getCommentByID(Integer id) {
        // TODO
        return "";
    }

    // Given the employee ID, get the comment that was intended for that employee from the HR
    public String getCommentByEmployeeId(Integer employeeId) {
        // TODO
        return "";
    }

    // Given the Application WorkFlow ID, get the application type
    public String getTypeById(Integer id) {
        // TODO
        return "";
    }

    // Given the Application WorkFlow ID, get the application's information
    public ApplicationWorkFlow getApplicationWorkFlowById(Integer id) {
        // TODO
        return new ApplicationWorkFlow();
    }
}
