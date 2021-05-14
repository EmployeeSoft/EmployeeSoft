package com.example.demo.service;

import com.example.demo.dao.InterfaceEmployeeDao;
import com.example.demo.dao.InterfacePersonalDocDao;
import com.example.demo.domain.PersonalDocumentDomain;
import com.example.demo.entity.PersonalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalDocService {
    private InterfacePersonalDocDao personalDocDao;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public void setPersonalDocDao(InterfacePersonalDocDao personalDocDao) {
        this.personalDocDao = personalDocDao;
    }

    @Transactional
    public PersonalDocumentDomain createPersonalDocumentByEmployeeId(Integer employeeId, String path, String filename,
                                                                     String fileTitle) {
        // Personal Document
        PersonalDocument document = personalDocDao.createPersonalDocumentByEmployeeId(employeeId, path, filename, fileTitle);

        PersonalDocumentDomain domain = PersonalDocumentDomain.builder()
                .id(document.getId())
                .employeeDomain(employeeService.getEmployeeByEmployeeId(employeeId))
                .path(document.getPath())
                .title(document.getTitle())
                .comment(document.getComment())
                .dateCreated(document.getDateCreated())
                .createdBy(document.getCreatedBy())
                .build();

        return domain;
    }
}
