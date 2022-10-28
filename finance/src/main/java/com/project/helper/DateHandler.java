package com.project.helper;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateHandler {

    private static final SimpleDateFormat dateUnFormat = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static String getUnFormattedIssueDate(Date date) {
        return dateUnFormat.format(date);
    }

    public static String getUnFormattedDueDate (String time) {
        Date dueDate = Date.from(ZonedDateTime.now().plusMonths(Long.parseLong(time)).toInstant());
        return dateUnFormat.format(dueDate);
    }

    public static String getFormattedDate(String date) {
        String year = date.substring(0,4);
        String month = date.substring(4,6);
        String day = date.substring(6,8);
        return MessageFormat.format("{0}-{1}-{2}",year,month,day);
    }

    public static String getRawDate(String text) {
        String[] dates = text.split("-");
        return MessageFormat.format("{0}{1}{2}", dates[0],dates[1],dates[2]);
    }

    public static String calculateDate(String text, String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(text, formatter);
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Date dueDate = Date.from(zonedDateTime.plusMonths(Long.parseLong(time)).toInstant());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(dueDate);
    }
}
