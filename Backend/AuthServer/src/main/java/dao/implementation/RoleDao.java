package dao.implementation;

import dao.InterfaceRoleDao;
import entity.Role;

import java.sql.Date;

public class RoleDao implements InterfaceRoleDao {
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
        // TODO
        return new Role();
    }
}
