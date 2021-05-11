package com.example.demo.test;

import com.example.demo.dao.implementation.EmployeeDao;
import com.example.demo.entity.Employee;

public class TestEmployeeDao {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        EmployeeDao employeeDao = new EmployeeDao();
        Employee employee = employeeDao.getEmployeeByEmployeeId(2);
        System.out.println(employee);
    }
}
