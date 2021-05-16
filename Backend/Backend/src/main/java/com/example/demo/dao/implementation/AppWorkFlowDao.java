package com.example.demo.dao.implementation;

import com.example.demo.config.HibernateConfig;
import com.example.demo.dao.InterfaceAppWorkFlowDao;
import com.example.demo.entity.Address;
import com.example.demo.entity.ApplicationWorkFlow;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.PersonService;
import com.example.demo.util.CalculateDate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppWorkFlowDao extends AbstractHibernateDao<ApplicationWorkFlow> implements InterfaceAppWorkFlowDao {
    public AppWorkFlowDao() { setClazz(ApplicationWorkFlow.class); }

    private PersonService personService;
    private EmployeeService employeeService;

    @Autowired
    public void setPersonService(PersonService personService) { this.personService = personService; }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) { this.employeeService = employeeService; }

    // Given the Application WorkFlow ID, get the application's information
    public ApplicationWorkFlow getApplicationWorkFlowById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM ApplicationWorkFlow WHERE id = :id");
        query.setParameter("id", id);
        System.out.println(query.list());
        ApplicationWorkFlow applicationWorkFlow = (ApplicationWorkFlow) query.uniqueResult();
        return applicationWorkFlow;
    }

    // Get a list of all application work flow by employee id
    public ArrayList<ApplicationWorkFlow> getAppWorkFlowsByEmployeeId(Integer employeeId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM ApplicationWorkFlow WHERE employeeId = :employeeId");
        query.setParameter("employeeId", employeeId);
        return (ArrayList<ApplicationWorkFlow>) query.list();
    }

    // Get the application workflow by user ID
    public ApplicationWorkFlow getCurrentByUserId(Integer userId) {
        Session session = getCurrentSession();
        String hql = "FROM ApplicationWorkFlow awf " +
                     "WHERE Employee.visaStatusId = awf.id " +
                     "AND Employee.personId = Person.id AND Person.userId = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        return (ApplicationWorkFlow) query.uniqueResult();
    }

    // Check to see if the employee has started an application
    public boolean checkEmployeeAppWorkFlowExist(Integer userId) {
        // Get the employee ID
        int employeeId = employeeService.getEmployeeIdByPersonId(
                personService.getPersonIdByUserId(userId)
        );

        Session session = getCurrentSession();
        String hql = "FROM ApplicationWorkFlow WHERE employeeId = :employeeId";
        Query query = session.createQuery(hql);
        query.setParameter("employeeId", employeeId);
        return query.list().size() >= 1;
    }

    // Get the current active workflow and change that to false
    public void updateCurrentAppWorkFlow(Integer userId) {
        // Get the employee ID
        int employeeId = employeeService.getEmployeeIdByPersonId(
                personService.getPersonIdByUserId(userId)
        );

        Session session = getCurrentSession();
        String hql = "UPDATE ApplicationWorkFlow SET status = 0 WHERE employeeId = :employeeId";
        Query query = session.createQuery(hql);
        query.setParameter("employeeId", employeeId);
        query.executeUpdate();
    }

    // First step in the process
    public Object createAppWorkFlow(Integer userId, String filename) {
        // Get the employee ID
        int employeeId = employeeService.getEmployeeIdByPersonId(
                personService.getPersonIdByUserId(userId)
        );

        // Get current date
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        // The comment will be based on the file type that the
        String comment = "";
        switch(filename) {
            case "OPT Receipt": case "opt receipt":
                comment = "Please upload a copy of your OPT EAD";
                break;
            case "OPT STEM Receipt": case "opt stem receipt":
                comment = "Please upload a copy of your OPT STEM EAD";
                break;
            case "OPT EAD": case "opt ead":
                comment = ""; // "Please download and fill your I-983 form";
                break;
            case "I-983": case "i-983":
                comment = "Waiting for HR to approve sign I-983";
                break;
            case "I-983 signed": case "i-983 signed": case "I-983 Signed": case "i-983 Signed":
                comment = "Please send the I-983 with all necessary documents to your school and upload the new I-20";
                break;
            case "I-20": case "i-20":
                comment = "Please upload your OPT STEM Receipt";
                break;
            case "OPT STEM EAD": case "opt stem ead":
                comment = "";
                break;
        }

        ApplicationWorkFlow awf = new ApplicationWorkFlow();
        awf.setEmployeeId(employeeId);
        awf.setDateCreated(date);
        awf.setDateModified(date);
        awf.setStatus(1);
        awf.setComment(comment);
        awf.setType(filename);

        Session session = getCurrentSession();
        System.out.println("Adding to database: " + filename);
        return session.merge(awf);
    }

    // See what is the most up-to-date file the user uploaded
    public String employeeMostCurrentUpload(Integer userId) {
        // Get the employee ID
        int employeeId = employeeService.getEmployeeIdByPersonId(
                personService.getPersonIdByUserId(userId)
        );
        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT title FROM PersonalDocument WHERE employeeId = :employeeId");
        query.setParameter("employeeId", employeeId);
        List<String> personalDocs = query.list();
        String type = "";

        return type;
    }

    // Get the comment
    public String getComment(Integer userId) {
        // Get the employee ID
        int employeeId = employeeService.getEmployeeIdByPersonId(
                personService.getPersonIdByUserId(userId)
        );

        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT comment FROM ApplicationWorkFlow WHERE employeeId = :employeeId AND status = 1");
        query.setParameter("employeeId", employeeId);
        return (String) query.uniqueResult();
    }

    // Get the type
    public String getType(Integer userId) {
        // Get the employee ID
        int employeeId = employeeService.getEmployeeIdByPersonId(
                personService.getPersonIdByUserId(userId)
        );

        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT type FROM ApplicationWorkFlow WHERE employeeId = :employeeId AND status = 1");
        query.setParameter("employeeId", employeeId);
        return (String) query.uniqueResult();
    }

    // Get the date created
    public Date getDateCreatedByUserId(Integer userId) {
        int employeeId = employeeService.getEmployeeIdByUserId(userId);

        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT dateCreated FROM ApplicationWorkFlow WHERE employeeId = :employeeId AND type = 'I-983'");
        query.setParameter("employeeId", employeeId);
        return (Date) query.uniqueResult();
    }

    // Get the date created
    public Date getDateModifiedByUserId(Integer userId) {
        int employeeId = employeeService.getEmployeeIdByUserId(userId);

        Session session = getCurrentSession();
        Query query = session.createQuery("SELECT dateModified FROM ApplicationWorkFlow WHERE employeeId = :employeeId AND type = 'I-983'");
        query.setParameter("employeeId", employeeId);
        return (Date) query.uniqueResult();
    }
}
