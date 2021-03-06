package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfaceRoleDao;
import com.example.demo.entity.Role;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class RoleDao extends AbstractHibernateDao<Role> implements InterfaceRoleDao {
    public RoleDao() {setClazz(Role.class);}

    // Given the role name, get the ID
    public int getRoleIdByRoleName(String roleName) {
        // TODO
        return 0;
    }

    // Given the ID, get the role name
    public String getRoleNameById(Integer id) {
        // TODO
        return "";
    }

    // Given the role name get the role description
    public String getDescriptionByRoleName(String roleName) {
        // TODO
        return "";
    }

    // Given the ID, get the description
    public String getDescriptionById(Integer id) {
        // TODO
        return "";
    }

    // Given the ID, get the date created
    public Date getDateCreatedById(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Given the role name, ge the date created
    public Date getDateCreatedByRoleName(String roleName) {
        // TODO
        return new Date(1234567890);
    }

    // Given the ID, get the date modified
    public Date getDateModifiedById(Integer id) {
        // TODO
        return new Date(1234567890);
    }

    // Give the role name, get the date modified
    public Date getDateModifiedByRoleName(String roleName) {
        // TODO
        return new Date(1234567890);
    }

    // Given the ID, get the role information
    public Role getRoleById(Integer id) {
        return getCurrentSession().get(Role.class, id);
    }
}
