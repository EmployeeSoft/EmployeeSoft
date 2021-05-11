package com.example.demo.test;

import com.example.demo.dao.implementation.PersonDao;
import com.example.demo.entity.Person;

import java.sql.Date;

public class TestPersonDao {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        PersonDao personDao = new PersonDao();
        Person person = personDao.getPersonById(2);
        System.out.println(person);
    }
}
