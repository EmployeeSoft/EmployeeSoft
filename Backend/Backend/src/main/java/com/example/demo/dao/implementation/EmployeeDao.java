package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfaceEmployeeDao;
import com.example.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.sql.Date;

@Repository
public class EmployeeDao extends AbstractHibernateDao<Employee> implements InterfaceEmployeeDao {
    public EmployeeDao() { setClazz(Employee.class); }

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


    ///// REQUIRED METHODS BELOW /////


    // Given the employee ID, get the manager ID
    public int getManagerIdByEmployeeId(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT managerId FROM Employee WHERE id = :id");
        query.setParameter("id", id);
        int mId = (int) query.uniqueResult();
        return mId;
    }

    // Given the employeeId, get the employee information
    public Employee getEmployeeByEmployeeId(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Employee WHERE id = :id");
        query.setParameter("id", id);
        Employee employee = (Employee) query.uniqueResult();
        return employee;
    }

    // Using the employee personId, determine if they hold a green card or a US citizen
    public boolean isStatusManagementAble(Integer personId) {
//        Session session1 = openSession();
//        Session session2 = openSession();
//
//        Query query1 = session1.createQuery("");
//        Query query2 = session2.createQuery("SELECT type FROM VisaStatus WHERE id = :id");

        return false;
    }

    // Given the person ID, get the employee avatar url link
    public String getAvatarLink(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT avatar FROM Employee WHERE personId = :personId");
        query.setParameter("personId", personId);
        String url = (String) query.uniqueResult();
        return url;
    }

    // Given the person ID, get the Visa Type as a string
    public String getVisaTypeByPersonId(Integer personId) {
        Session session1 = openSession();
        Session session2 = openSession();

        Query query1 = session1.createQuery("SELECT visaStatus FROM Employee WHERE personId = :personId");
        query1.setParameter("personId", personId);
        int visaId = (int) query1.uniqueResult();

        Query query2 = session2.createQuery("SELECT visaType FROM VisaStatus WHERE id = :visaId");
        query2.setParameter("visaId", visaId);
        String visaType = (String) query2.uniqueResult();
        return visaType;
    }

    // Given the person ID, get the visa start date
    public Date getVisaStartDateByPersonId(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT visaStartDate FROM Employee WHERE personId = :personId");
        query.setParameter("personId", personId);
        Date date = (Date) query.uniqueResult();
        return date;
    }

    // Given the person ID, get the visa end date
    public Date getVisaEndDateByPersonId(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT visaEndDate FROM Employee WHERE personId = :personId");
        query.setParameter("personId", personId);
        Date date = (Date) query.uniqueResult();
        return date;
    }

    // Given the person ID, get the employee start date
    public Date getEmployeeStartDateByPersonId(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT startDate FROM Employee WHERE personId = :personId");
        query.setParameter("personId", personId);
        Date date = (Date) query.uniqueResult();
        return date;
    }

    // Given the person ID, get the employee end date
    public Date getEmployeeEndDateByPersonId(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT endDate FROM Employee WHERE personId = :personId");
        query.setParameter("personId", personId);
        Date date = (Date) query.uniqueResult();
        return date;
    }

    // Get the employee title by person ID
    public String getTitleByPersonId(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT title FROM Employee WHERE personId = :personId");
        query.setParameter("personId", personId);
        String title = (String) query.uniqueResult();
        return title;
    }

    // Get the employee car
    public String getCarByPersonId(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT car FROM Employee WHERE personId = :personId");
        query.setParameter("personId", personId);
        String car = (String) query.uniqueResult();
        return car;
    }
}
