package com.indicia.indicia.lib;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTime {

    private static SimpleDateFormat formatter;
    private static String bangla = "০১২৩৪৫৬৭৮৯";

    static {
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
    }

    public static String getDateTimeForChatBot() {
        Date date = new Date();
        String dateTimeS = formatter.format(date);
        char[] dateTime = dateTimeS.toCharArray();
        for (int i = 0; i < dateTime.length; i++) {
            if (dateTime[i] >= '0' && dateTime[i] <= '9') {
                dateTime[i] = bangla.charAt(dateTime[i] - '0');
            }
        }
        return String.valueOf(dateTime);
    }
}
