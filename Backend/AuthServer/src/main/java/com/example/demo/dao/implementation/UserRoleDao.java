package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfaceUserRoleDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class UserRoleDao extends AbstractHibernateDao<UserRole> implements InterfaceUserRoleDao {
    public UserRoleDao() {setClazz(UserRole.class);}

    // Given the user role ID, get the user role
    public UserRole getUserRoleById(Integer id) {
        // TODO
        return new UserRole();
    }

    // Given the user ID, get the user role
    public UserRole getUserRoleByUserId(Integer userId) {
        // TODO
        return new UserRole();
    }

    // Get the user role ID from the user ID
    public int getUserRoleIdByUserId(Integer userId) {
        // TODO
        return 0;
    }

    // Given the user role ID, get the role ID
    public int getRoleIdById(Integer id) {
        // TODO
        return 0;
    }

    // Given the user role ID, get the active flag
    public boolean getActiveFlagById(Integer id) {
        // TODO
        return false;
    }

    // Given the user role ID, get the date created
    public Date getDateCreatedById(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the user role ID, get the date last modified
    public Date getDateModifiedById(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    @Override
    public void createUserRole(UserRole userRole) {
        merge(userRole);
    }

    @Override
    public Role getRoleByUser(User user) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM UserRole ur WHERE ur.user.id = :userId");
        System.out.println(user.getId());
        query.setParameter("userId", user.getId());
        UserRole userRole = (UserRole) query.uniqueResult();
        if (userRole == null) {
            System.out.println("userRoll null");
            return null;
        }
        return userRole.getRole();
    }
}
