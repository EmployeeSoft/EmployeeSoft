package com.example.demo.test;

import com.example.demo.dao.implementation.ContactDao;
import com.example.demo.entity.Contact;

import java.util.List;

public class TestContactDao {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        ContactDao contactDao = new ContactDao();
        List<Contact> contacts = contactDao.getContactByReference(1);
        for (Contact contact : contacts) System.out.println(contact);
    }
}
