package com.example.demo.service;

import com.example.demo.dao.InterfaceVisaStatusDao;
import com.example.demo.dao.implementation.VisaStatusDao;
import com.example.demo.domain.VisaStatusDomain;
import com.example.demo.entity.VisaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisaStatusService {
    private InterfaceVisaStatusDao visaStatusDao;

    @Autowired
    public void setVisaStatusDao(InterfaceVisaStatusDao visaStatusDao) {
        this.visaStatusDao = visaStatusDao;
    }

    @Transactional
    public VisaStatusDomain getVisaStatusById(Integer id) {
        VisaStatus visaStatus = visaStatusDao.getVisaStatusById(id);
        VisaStatusDomain visaStatusDomain = VisaStatusDomain.builder()
                .id(visaStatus.getId())
                .visaType(visaStatus.getVisaType())
                .isActive(visaStatus.getIsActive())
                .dateModified(visaStatus.getDateModified())
                .createUser(visaStatus.getCreateUser())
                .build();

        return visaStatusDomain;
    }

    @Transactional
    public Boolean visaStatusManagementAble(String createUser) {
        return visaStatusDao.visaStatusManagementAble(createUser);
    }

    @Transactional
    public String getCreateUserById(Integer id) {
        return visaStatusDao.getCreateUserById(id);
    }
}
