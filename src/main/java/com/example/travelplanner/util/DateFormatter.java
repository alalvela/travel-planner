package com.example.travelplanner.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateFormatter {

    private static final String datePattern = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

    public static String getStringFromLocalDate(LocalDate localDate) {
        return localDate.format(formatter);
    }

    public static LocalDate getLocalDateFromString(String dateString) {
        return LocalDate.parse(dateString, formatter);
    }
}
