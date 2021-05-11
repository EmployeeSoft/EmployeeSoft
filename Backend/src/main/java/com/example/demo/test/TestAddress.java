package com.example.demo.test;

import com.example.demo.dao.implementation.AddressDao;
import com.example.demo.entity.Address;

import java.util.List;

public class TestAddress {
    public static void test() {
        AddressDao addressDao = new AddressDao();
//        String result = addressDao.getStateAbbrByPersonId(1);
        Address result = addressDao.getAddressById(1);

        System.out.println(result);
    }

    public static void main(String[] args) {
        test();
    }
}
