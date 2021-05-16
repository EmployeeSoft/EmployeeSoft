package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfaceEmployeeDao;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

@Repository
public class EmployeeDao extends AbstractHibernateDao<Employee> implements InterfaceEmployeeDao {
    public EmployeeDao() { setClazz(Employee.class); }

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
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT e.visaStatus.visaType FROM Employee e WHERE e.person.id = :personId");
        query.setParameter("personId", personId);
        String visaType = (String) query.uniqueResult();
        return !visaType.equals("Green Card") && !visaType.equals("Citizen");
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
        int employeeId = getEmployeeIdByPersonId(personId);
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT visaType FROM VisaStatus WHERE id = :employeeId");
        query.setParameter("employeeId", employeeId);
        String visaType = (String) query.uniqueResult();
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

    // Get the employee information from the person ID
    public Employee getEmployeeByPersonId(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Employee WHERE personId = :personId");
        query.setParameter("personId", personId);
        Employee employee = (Employee) query.uniqueResult();
        return employee;
    }

    // Given the personId, merge the file
    public void updateEmployeeAvatarByPersonId(Integer personId, String avatar) {
        Session session = getCurrentSession();
        Query query = session.createQuery("UPDATE Employee SET avatar =: avatar WHERE personId = :personId");
        query.setParameter("avatar", avatar);
        query.setParameter("personId", personId);
        query.executeUpdate();
    }


    @Override
    public int addNewEmployee(Employee employee) {
        Session session = getCurrentSession();
        return (int) session.save(employee);
    }

    // Given the person ID, get their employee ID
    public int getEmployeeIdByPersonId(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT id FROM Employee WHERE personId = :personId");
        query.setParameter("personId", personId);
        return (int) query.uniqueResult();
    }

    // Get the employee's name
    public String getFirstNameByEmployeeId(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT e.person.firstName FROM Employee e WHERE e.person.id = :id");
        query.setParameter("id", id);
        return (String) query.uniqueResult();
    }

    // Get all employees for HR
    public ArrayList<Employee> getAllEmployees() {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Employee");
        return (ArrayList<Employee>) query.list();
    }
}
