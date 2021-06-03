package com.github.vladislavgoltjajev.personalcode.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public final class DateUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    public static String getFormattedDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    private DateUtils() {
    }
}
