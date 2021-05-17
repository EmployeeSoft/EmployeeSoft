package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
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

    public static long checkDaysLeft(java.sql.Date dateCreated, java.sql.Date dateModified, String filename) {
        // Result, the number of days left before expiration
        long daysLeft = 0;

        // Convert date to java.util
        java.util.Date dateCreatedUtil = new java.util.Date(dateCreated.getTime());

        Calendar c1 = Calendar.getInstance();
        c1.setTime(dateCreatedUtil);

        if (filename.equals("F1-OPT")) {
            c1.add(Calendar.YEAR, 1);   // OPT expires in 1 year
        } else {
            c1.add(Calendar.YEAR, 2);   // OPT STEM expires in 2 years
        }


        // Convert Calendar to java.util.Date
        java.util.Date dateAfterOneYearUtil = c1.getTime();

        // Convert java.util.Date to java.sql.Date
        java.sql.Date dateAfterOneYear = new java.sql.Date(dateAfterOneYearUtil.getTime());

        daysLeft = ChronoUnit.DAYS.between(dateModified.toLocalDate(), dateAfterOneYear.toLocalDate());

        return daysLeft;
    }

    public static java.sql.Date getExpirationDate(java.sql.Date sqlDate, String fileTitle) {
        java.util.Date dateUtil = new java.util.Date(sqlDate.getTime());

        Calendar c1 = Calendar.getInstance();
        c1.setTime(dateUtil);

        if (fileTitle.equals("F1-OPT")) {
            c1.add(Calendar.YEAR, 1);
        } else {
            c1.add(Calendar.YEAR, 2);
        }

        // Convert to java.util.Date
        java.util.Date expirationdateUtil = c1.getTime();

        // Convert to java.sql.Date
        java.sql.Date expirationDate = new java.sql.Date(expirationdateUtil.getTime());

        return expirationDate;
    }

}
