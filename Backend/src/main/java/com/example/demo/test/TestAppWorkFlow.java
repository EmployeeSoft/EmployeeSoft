package com.example.demo.test;

import com.example.demo.dao.implementation.AppWorkFlowDao;
import com.example.demo.entity.ApplicationWorkFlow;

import java.sql.Date;

public class TestAppWorkFlow {
    public static void test() {
        AppWorkFlowDao appWorkFlowDao = new AppWorkFlowDao();
        ApplicationWorkFlow result = appWorkFlowDao.getApplicationWorkFlowById(2);
        System.out.println("Result: " + result);
    }
    public static void main(String[] args) {
        test();
    }
}
