package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalculateDate {
    // Method used to determine if the current date is less than 100 days
    public static boolean hundredDays(java.sql.Date dateCreated, java.sql.Date dateModified) {
        // Convert java.sql.Date to java.util.Date
        final java.util.Date dateCreatedUtil = new java.util.Date(dateCreated.getTime());
        final java.util.Date dateModifiedUtil = new java.util.Date(dateModified.getTime());

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(dateCreatedUtil);
        c2.setTime(dateCreatedUtil);


        // Add one year to the current {sqlDate}
        c1.add(Calendar.YEAR, 1);

        c2.add(Calendar.YEAR, 1);
        c2.add(Calendar.DAY_OF_MONTH, -100);

        // Get the new Date
        java.util.Date dateAfterOneYear = c1.getTime();
        java.util.Date dateAfterOneYearMinus100Days = c2.getTime();

        return dateModifiedUtil.after(dateAfterOneYearMinus100Days) && dateModifiedUtil.before(dateAfterOneYear);
    }
}
