package com.example.demo.test;

import com.example.demo.dao.implementation.DigitalDocDao;
import com.example.demo.entity.DigitalDocument;

import java.util.List;

public class TestDigitalDocumentDao {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        DigitalDocDao digitalDocDao = new DigitalDocDao();
        List<DigitalDocument> digitalDocuments = digitalDocDao.getAddDigitalDocuments();
        for (DigitalDocument document : digitalDocuments) System.out.println(document);
    }
}
