package com.example.demo.service;

import com.example.demo.dao.InterfacePersonalDocDao;
import com.example.demo.domain.PersonalDocumentDomain;
import com.example.demo.entity.Employee;
import com.example.demo.entity.PersonalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

@Service
public class PersonalDocService {
    private InterfacePersonalDocDao personalDocDao;

    @Autowired
    public void setPersonalDocDao(InterfacePersonalDocDao personalDocDao) {
        this.personalDocDao = personalDocDao;
    }

    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    public PersonalDocumentDomain createPersonalDocumentByEmployeeId(Integer employeeId, String path, String filename,
                                                                     String fileTitle) {
        // Personal Document
        PersonalDocument document = (PersonalDocument) personalDocDao.createPersonalDocumentByEmployeeId(employeeId, path, filename, fileTitle);

        if (document != null) {
            PersonalDocumentDomain domain = PersonalDocumentDomain.builder()
                    .id(document.getId())
//                    .employeeDomain(employeeService.getEmployeeByEmployeeId(employeeId))
                    .path(document.getPath())
                    .title(document.getTitle())
                    .comment(document.getComment())
                    .dateCreated(df.format(document.getDateCreated()))
                    .createdBy(document.getCreatedBy())
                    .build();

            return domain;
        } else {
            return null;
        }
    }

    @Transactional
    public ArrayList<PersonalDocumentDomain> getPersonalDocsByEmployeeId(Integer employeeId) {
        ArrayList<PersonalDocumentDomain> domains = new ArrayList<>();

        // Loop through documents
        for (PersonalDocument document : personalDocDao.getPersonalDocsByEmployeeId(employeeId)) {
            PersonalDocumentDomain domain = PersonalDocumentDomain.builder()
                    .id(document.getId())
                    .path(document.getPath())
                    .title(document.getTitle())
                    .comment(document.getComment())
                    .dateCreated(df.format(document.getDateCreated()))
                    .createdBy(document.getCreatedBy())
                    .build();
            domains.add(domain);
        }

        return domains;
    }

    @Transactional
    public String getPath(Integer userId, String fileTitle) {
        return personalDocDao.getPath(userId, fileTitle);
    }

    @Transactional
    public Map<String, String> getEmployeeFilePaths(Integer userId) {
        return personalDocDao.getEmployeeFilePaths(userId);
    }
}
