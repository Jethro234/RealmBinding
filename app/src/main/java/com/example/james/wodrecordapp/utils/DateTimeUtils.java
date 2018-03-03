package com.example.james.wodrecordapp.utils;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jimmy on 24/01/2018.
 */

public class DateTimeUtils {
    private static final DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.UK);
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);


    public static String getCurrentDate() {
        return formatDate(DateTime.now().toDate());
    }

    public static String getCurrentDateTime() {
        return formatDateTime(DateTime.now().toDateTime());
    }

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public static String formatDateTime(DateTime dateTime) {
        return dateTimeFormat.format(dateTime);
    }
}
