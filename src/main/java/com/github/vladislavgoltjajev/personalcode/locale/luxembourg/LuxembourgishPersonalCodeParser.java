package com.github.vladislavgoltjajev.personalcode.locale.luxembourg;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public final class LuxembourgishPersonalCodeParser {

    /**
     * Returns the person's date of birth.
     * Digits 1 through 8 of the Luxembourgish personal code show the person's date of birth in the format YYYYMMDD.
     *
     * @param personalCode Luxembourgish personal code.
     * @return Date of birth.
     * @throws PersonalCodeException If the personal code is not a valid Luxembourgish personal code or the person's
     *                               date of birth is invalid.
     */
    public LocalDate getDateOfBirth(String personalCode) throws PersonalCodeException {
        return getDateOfBirth(personalCode, true);
    }

    LocalDate getDateOfBirth(String personalCode, boolean validate) throws PersonalCodeException {
        if (validate) {
            validatePersonalCode(personalCode);
        }

        try {
            return LocalDate.parse(personalCode.substring(0, 8), LuxembourgishPersonalCodeConstants.DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new PersonalCodeException(e.getMessage());
        }
    }

    /**
     * Returns the person's age.
     *
     * @param personalCode Luxembourgish personal code.
     * @return Period object containing the person's age.
     * @throws PersonalCodeException If the personal code is not a valid Luxembourgish personal code or the person's date of birth
     *                               is invalid or in the future.
     */
    public Period getAge(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
        LocalDate dateOfBirth = getDateOfBirth(personalCode);

        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new PersonalCodeException("Date of birth is in the future");
        }

        return Period.between(dateOfBirth, LocalDate.now());
    }

    /**
     * Returns the person's birth order number (digits 9-11).
     *
     * @param personalCode Luxembourgish personal code.
     * @return Birth order number.
     * @throws PersonalCodeException If the personal code is not a valid Luxembourgish personal code.
     */
    public int getBirthOrderNumber(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
        return Integer.parseInt(personalCode.substring(8, 11));
    }

    private void validatePersonalCode(String personalCode) throws PersonalCodeException {
        LuxembourgishPersonalCodeValidator validator = new LuxembourgishPersonalCodeValidator();

        if (!validator.isValid(personalCode)) {
            throw new PersonalCodeException("Invalid Luxembourgish personal code");
        }
    }
}
