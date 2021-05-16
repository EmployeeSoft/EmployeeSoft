package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalculateDate {
    // Method used to determine if the current date is less than 100 days
    public static boolean hundredDays(java.sql.Date sqlDate) {
        // Convert java.sql.Date to java.util.Date
        java.util.Date currentDateUtil = new java.util.Date(sqlDate.getTime());

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(currentDateUtil);
        c2.setTime(currentDateUtil);

        // Add one year to the current {sqlDate}
        c1.add(Calendar.YEAR, 1);

        // Add one year and subtract 100 days
        c2.add(Calendar.YEAR, 1);
        c2.add(Calendar.DAY_OF_MONTH, -100);

        // Get the new Date
        java.util.Date dateAfterOneYear = c1.getTime();
        java.util.Date dateAfterOneYearMinus100Days = c2.getTime();

        // Get the current date
        java.util.Date currentDate = java.util.Calendar.getInstance().getTime();

        return currentDate.after(dateAfterOneYearMinus100Days) && currentDate.before(dateAfterOneYear);
    }
}
