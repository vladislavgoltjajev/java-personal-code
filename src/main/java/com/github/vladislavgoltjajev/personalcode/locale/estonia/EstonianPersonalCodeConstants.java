package com.github.vladislavgoltjajev.personalcode.locale.estonia;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

class EstonianPersonalCodeConstants {

    /**
     * Does not account for leap years (29th of February).
     */
    static final String PERSONAL_CODE_REGEX = "^[1-6]\\d{2}(((0[13578]|1[02])(0[1-9]|[12]\\d|3[01]))"
            + "|((0[469]|11)(0[1-9]|[12]\\d|30))"
            + "|(02(0[1-9]|1\\d|2[0-9])))"
            + "\\d{4}$";
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuuMMdd")
            .withResolverStyle(ResolverStyle.STRICT);
    static final LocalDate MINIMUM_DATE = LocalDate.of(1800, Month.JANUARY, 1);
    static final LocalDate MAXIMUM_DATE = LocalDate.of(2099, Month.DECEMBER, 31);

    private EstonianPersonalCodeConstants() {
    }
}
