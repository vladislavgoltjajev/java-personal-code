package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

final class LatvianPersonalCodeConstants {

    /**
     * Does not account for leap years (29th of February).
     */
    static final String LEGACY_LATVIAN_PERSONAL_CODE_REGEX = "^(((0[1-9]|[12]\\d|3[01])(0[13578]|1[02]))"
            + "|((0[1-9]|[12]\\d|30)(0[469]|11))|((0[1-9]|1\\d|2[0-9])02))"
            + "\\d{2}-[0-2]\\d{4}$";
    static final String LATVIAN_PERSONAL_CODE_REGEX = "^32\\d{4}-?\\d{5}$";
    static final DateTimeFormatter LEGACY_DATE_FORMATTER = DateTimeFormatter.ofPattern("ddMMuuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    static final LocalDate MINIMUM_LEGACY_DATE = LocalDate.of(1800, Month.JANUARY, 1);
    static final LocalDate MAXIMUM_LEGACY_DATE = LocalDate.of(2017, Month.JUNE, 30);

    private LatvianPersonalCodeConstants() {
    }
}
