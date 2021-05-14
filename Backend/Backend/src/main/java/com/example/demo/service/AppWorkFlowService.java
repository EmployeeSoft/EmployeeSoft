package com.example.demo.service;

import com.example.demo.dao.InterfaceAppWorkFlowDao;
import com.example.demo.domain.ApplicationWorkFlowDomain;
import com.example.demo.entity.ApplicationWorkFlow;
import com.example.demo.exceptions.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
public class AppWorkFlowService {
    private InterfaceAppWorkFlowDao appWorkFlowDao;

    @Autowired
    public void setAppWorkFlow(InterfaceAppWorkFlowDao appWorkFlowDao) {
        this.appWorkFlowDao = appWorkFlowDao;
    }

    @Transactional
    public ApplicationWorkFlowDomain getApplicationWorkFlowById(Integer id) {
        try {
            ApplicationWorkFlow applicationWorkFlow = appWorkFlowDao.getApplicationWorkFlowById(id);

            ApplicationWorkFlowDomain domain = ApplicationWorkFlowDomain.builder()
                    .id(applicationWorkFlow.getId())
                    .dateCreated(applicationWorkFlow.getDateCreated())
                    .dateModified(applicationWorkFlow.getDateModified())
                    .status(applicationWorkFlow.getStatus())
                    .comment(applicationWorkFlow.getComment())
                    .type(applicationWorkFlow.getType())
                    .build();

            return domain;
        } catch (Exception e) {
            throw new MyException(e);
        }
    }

    @Transactional
    public ArrayList<ApplicationWorkFlowDomain> getAppWorkFlowsByEmployeeId(Integer employeeId) {
        ArrayList<ApplicationWorkFlowDomain> domains = new ArrayList<>();

        for (ApplicationWorkFlow app : appWorkFlowDao.getAppWorkFlowsByEmployeeId(employeeId)) {
            ApplicationWorkFlowDomain domain = ApplicationWorkFlowDomain.builder()
                    .id(app.getId())
                    .dateCreated(app.getDateCreated())
                    .dateModified(app.getDateModified())
                    .status(app.getStatus())
                    .comment(app.getComment())
                    .type(app.getType())
                    .build();

            domains.add(domain);
        }
        return domains;
    }
}
