package com.github.vladislavgoltjajev.personalcode.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    public static String getReadableFormatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    /**
     * Generates a random date in the specified range (inclusive).
     *
     * @param startDate Start date.
     * @param endDate   End date.
     * @return Random date in the specified range.
     */
    public static LocalDate getRandomDate(LocalDate startDate, LocalDate endDate) {
        long leftLimit = 0L;
        long rightLimit = ChronoUnit.DAYS.between(startDate, endDate);
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        return startDate.plusDays(generatedLong);
    }

    private DateUtils() {
    }
}
