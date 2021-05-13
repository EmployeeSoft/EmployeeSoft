package com.example.demo.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class CalculateAge {
    public static int age(java.sql.Date dob) {
        // Convert java.sql.Date to java.util.Date
        java.util.Date dobUtil = new java.util.Date(dob.getTime());

        // Convert java.util.Date to LocalDate
        LocalDate birthday = convertToLocalDate(dobUtil);

        // Get the current date
        LocalDate now = LocalDate.now();

        return Period.between(birthday, now).getYears();
    }

    public static LocalDate convertToLocalDate(java.util.Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
