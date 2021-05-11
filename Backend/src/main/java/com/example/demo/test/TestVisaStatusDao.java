package com.example.demo.test;

import com.example.demo.dao.implementation.VisaStatusDao;
import com.example.demo.entity.VisaStatus;

public class TestVisaStatusDao {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        VisaStatusDao visaStatusDao = new VisaStatusDao();
        String visaStatus = visaStatusDao.getCreateUserById(1);
        System.out.println(visaStatus);
    }
}
