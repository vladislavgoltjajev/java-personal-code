package com.github.vladislavgoltjajev.personalcode.utility;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public final class DateUtils {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuuMMdd")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final LocalDate MINIMUM_DATE = LocalDate.of(1800, Month.JANUARY, 1);
    public static final LocalDate MAXIMUM_DATE = LocalDate.of(2099, Month.DECEMBER, 31);

    private DateUtils() {
    }
}
