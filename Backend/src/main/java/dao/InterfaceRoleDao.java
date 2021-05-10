package dao;

import entity.Role;

import java.sql.Date;

public interface InterfaceRoleDao {
    // Given the role name, get the ID
    int getRoleIdByRoleName(String roleName);

    // Given the ID, get the role name
    String getRoleNameById(Integer id);

    // Given the role name get the role description
    String getDescriptionByRoleName(String roleName);

    // Given the ID, get the description
    String getDescriptionById(Integer id);

    // Given the ID, get the date created
    Date getDateCreatedById(Integer id);

    // Given the role name, ge the date created
    Date getDateCreatedByRoleName(String roleName);

    // Given the ID, get the date modified
    Date getDateModifiedById(Integer id);

    // Give the role name, get the date modified
    Date getDateModifiedByRoleName(String roleName);

    // Given the ID, get the role information
    Role getRoleById(Integer id);
}
