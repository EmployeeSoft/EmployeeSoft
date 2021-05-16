package com.example.demo.service;

import com.example.demo.dao.InterfaceAppWorkFlowDao;
import com.example.demo.domain.ApplicationWorkFlowDomain;
import com.example.demo.entity.ApplicationWorkFlow;
import com.example.demo.exceptions.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


@Service
public class AppWorkFlowService {
    private InterfaceAppWorkFlowDao appWorkFlowDao;

    @Autowired
    public void setAppWorkFlow(InterfaceAppWorkFlowDao appWorkFlowDao) {
        this.appWorkFlowDao = appWorkFlowDao;
    }

    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    public ApplicationWorkFlowDomain getApplicationWorkFlowById(Integer id) {
        try {
            ApplicationWorkFlow applicationWorkFlow = appWorkFlowDao.getApplicationWorkFlowById(id);

            ApplicationWorkFlowDomain domain = ApplicationWorkFlowDomain.builder()
                    .id(applicationWorkFlow.getId())
                    .dateCreated(df.format(applicationWorkFlow.getDateCreated()))
                    .dateModified(df.format(applicationWorkFlow.getDateModified()))
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
                    .dateCreated(df.format(app.getDateCreated()))
                    .dateModified(df.format(app.getDateModified()))
                    .status(app.getStatus())
                    .comment(app.getComment())
                    .type(app.getType())
                    .build();

            domains.add(domain);
        }
        return domains;
    }

    @Transactional
    public ApplicationWorkFlowDomain getCurrentByUserId(Integer userId) {
        ApplicationWorkFlow applicationWorkFlow = appWorkFlowDao.getCurrentByUserId(userId);

        ApplicationWorkFlowDomain domain = ApplicationWorkFlowDomain.builder()
                .id(applicationWorkFlow.getId())
                .dateCreated(df.format(applicationWorkFlow.getDateCreated()))
                .dateModified(df.format(applicationWorkFlow.getDateModified()))
                .status(applicationWorkFlow.getStatus())
                .comment(applicationWorkFlow.getComment())
                .type(applicationWorkFlow.getType())
                .build();

        return domain;
    }

    @Transactional
    public boolean checkEmployeeAppWorkFlowExist(Integer userId) {
        return appWorkFlowDao.checkEmployeeAppWorkFlowExist(userId);
    }

    @Transactional
    public void updateCurrentAppWorkFlow(Integer userId) {
        appWorkFlowDao.updateCurrentAppWorkFlow(userId);
    }

    @Transactional
    public void createAppWorkFlow(Integer userId, String filename) {
        Object obj = appWorkFlowDao.createAppWorkFlow(userId, filename);
    }

    @Transactional
    public String getComment(Integer userId) {
        return appWorkFlowDao.getComment(userId);
    }

    @Transactional
    public String getType(Integer userId) {
        return appWorkFlowDao.getType(userId);
    }
}
