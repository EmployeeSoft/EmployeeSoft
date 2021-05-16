package com.example.demo.service;

import com.example.demo.dao.InterfaceDigitalDocDao;
import com.example.demo.domain.DigitalDocumentDomain;
import com.example.demo.entity.DigitalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DigitalDocumentService {
    private InterfaceDigitalDocDao digitalDocDao;

    @Autowired
    public void setDigitalDocDao(InterfaceDigitalDocDao digitalDocDao) {
        this.digitalDocDao = digitalDocDao;
    }

    @Transactional
    public ArrayList<DigitalDocumentDomain> getAddDigitalDocuments() {
        List<DigitalDocument> digitalDocuments = digitalDocDao.getAddDigitalDocuments();
        ArrayList<DigitalDocumentDomain> digitalDocumentDomains = new ArrayList<>();

        for (DigitalDocument digitalDocument : digitalDocuments) {
            DigitalDocumentDomain domain = DigitalDocumentDomain.builder()
                    .id(digitalDocument.getId())
                    .type(digitalDocument.getType())
                    .required(digitalDocument.getRequired())
                    .templateLocation(digitalDocument.getTemplateLocation())
                    .description(digitalDocument.getDescription())
                    .build();
            digitalDocumentDomains.add(domain);
        }

        return digitalDocumentDomains;
    }

    @Transactional
    public ArrayList<DigitalDocumentDomain> getRequiredDocuments() {
        List<DigitalDocument> digitalDocuments = digitalDocDao.getRequiredDocuments();
        ArrayList<DigitalDocumentDomain> digitalDocumentDomains = new ArrayList<>();

        for (DigitalDocument digitalDocument : digitalDocuments) {
            DigitalDocumentDomain domain = DigitalDocumentDomain.builder()
                    .id(digitalDocument.getId())
                    .type(digitalDocument.getType())
                    .required(digitalDocument.getRequired())
                    .templateLocation(digitalDocument.getTemplateLocation())
                    .description(digitalDocument.getDescription())
                    .build();
            digitalDocumentDomains.add(domain);
        }

        return digitalDocumentDomains;
    }

    @Transactional
    public String getDigitalDocument(String filename) {
        return digitalDocDao.getDigitalDocument(filename);
    }
}
