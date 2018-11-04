package com.example.travelplanner.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public final class DateUtils {

    private static final String datePattern = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

    public static String getStringFromLocalDate(LocalDate localDate) {
        return localDate.format(formatter);
    }

    public static LocalDate getLocalDateFromString(String dateString) {
        return LocalDate.parse(dateString, formatter);
    }

    public static String fromInstantToString(Instant instant) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                        .withLocale(Locale.UK)
                        .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    public static int getDayIntFromLocalDate(LocalDate date) {
        return date.getDayOfMonth();
    }

    public static int getMonthIntFromLocalDate(LocalDate date) {
        return date.getMonthValue();
    }

    public static int getYearIntFromLocalDate(LocalDate date) {
        return date.getYear();
    }
}
