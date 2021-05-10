package dao.implementation;

import dao.InterfaceEmployeeDao;

import java.io.File;
import java.sql.Date;

public class EmployeeDao implements InterfaceEmployeeDao {
    // Given the person ID, get their employee ID
    public int getEmployeeIdByPersonId(Integer id) {
        // TODO
        return 0;
    }

    // Given the employee ID, get that employee's person ID
    public int getPersonIdByEmployeeId(Integer id) {
        // TODO
        return 0;
    }

    // Given the employee ID, get the title
    public String getTitleByEmployeeId(Integer id) {
        // TODO
        return "";
    }

    // Given the employee ID, get the manager ID
    public int getManagerIdByEmployeeId(Integer id) {
        // TODO
        return 0;
    }

    // Given the employee ID, get the start date of that employee
    public Date getStartDateByEmployeeId(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the employee ID, get the end date of the employee
    public Date getEndDateByEmployeeId(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Get the employee's avatar pic from the database using the employee ID
    public File getAvatarByEmployeeId(Integer id) {
        // TODO
        return new File("");
    }

    // Given the employee ID, get the employee's car information
    public String getCarIntoByEmployeeId(Integer id) {
        // TODO
        return "";
    }

    // Given the employee ID, get the visa status ID
    public int getVisaStatusIdByEmployeeId(Integer id) {
        // TODO
        return 0;
    }

    // Given the employee ID, get the visa start date
    public Date getVisaStartDateByEmployeeId(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the employee ID, get the visa end date
    public Date getVisaEndDateByEmployeeId(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Get the employee's driver license number by using the employee ID
    public String getDriverLicenseByEmployeeId(Integer id) {
        // TODO
        return "";
    }

    // Given the employee ID, get the employee's driver license expire date
    public Date getDriverLicenseExpDateByEmployeeId(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the driver license number, get the license exp date
    public Date getDriverLicenseExpDateByLicenseNumber(String driverLicenseNum) {
        // TODO
        return new Date(1234567890);
    }
}
