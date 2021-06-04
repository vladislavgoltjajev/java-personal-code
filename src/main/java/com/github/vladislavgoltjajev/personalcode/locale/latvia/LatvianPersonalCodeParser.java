package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

import java.time.LocalDate;
import java.time.Period;

public final class LatvianPersonalCodeParser {

    /**
     * Returns a Period object containing the person's age.
     *
     * @param personalCode Legacy Latvian personal code.
     * @return Period object containing the person's age.
     * @throws PersonalCodeException If the personal code is not a valid legacy Latvian personal code or the date of birth is after the
     *                               introduction of the updated Latvian personal code format.
     */
    public Period getAge(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
        LocalDate dateOfBirth = getDateOfBirth(personalCode);

        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new PersonalCodeException("Date of birth is after the introduction of the updated Latvian personal code format");
        }

        return Period.between(dateOfBirth, LocalDate.now());
    }

    public LocalDate getDateOfBirth(String personalCode) throws PersonalCodeException {
        return getDateOfBirth(personalCode, true);
    }

    /**
     * Returns the person's date of birth.
     * Digits 1 through 6 of the legacy Latvian personal code show the person's birth date in the format yyddMM.
     * Using the century identifier (seventh digit), it is possible to acquire the person's full year of birth.
     * 0 - years 1800-1899.
     * 1 - years 1900-1999.
     * 2 - years 2000-2099.
     *
     * @param personalCode Legacy Latvian personal code.
     * @param validate     Whether or not to skip the Latvian personal code validation.
     * @return Date of birth.
     * @throws PersonalCodeException If the personal code is not a valid legacy Latvian personal code.
     */
    LocalDate getDateOfBirth(String personalCode, boolean validate) throws PersonalCodeException {
        if (validate) {
            validatePersonalCode(personalCode);
        }

        String yearStart;

        switch (getCenturyIdentifier(personalCode)) {
            case 0:
                yearStart = "18";
                break;
            case 1:
                yearStart = "19";
                break;
            default:
                yearStart = "20";
        }

        String dateString = personalCode.substring(0, 4) + yearStart + personalCode.substring(4, 6);
        return LocalDate.parse(dateString, LatvianPersonalCodeConstants.LEGACY_DATE_FORMATTER);
    }

    /**
     * Returns the birth order number (digits 8-10) in the legacy Latvian personal code.
     *
     * @param personalCode Legacy Latvian personal code.
     * @return Birth order number.
     * @throws PersonalCodeException If the personal code is not a valid legacy Latvian personal code.
     */
    public int getBirthOrderNumber(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
        return Integer.parseInt(personalCode.substring(8, 9));
    }

    /**
     * Returns the century identifier (seventh digit) from the legacy Latvian personal code.
     *
     * @param personalCode Latvian personal code.
     * @return Gender identifier.
     */
    private int getCenturyIdentifier(String personalCode) {
        return Character.getNumericValue(personalCode.charAt(0));
    }

    private void validatePersonalCode(String personalCode) throws PersonalCodeException {
        LatvianPersonalCodeValidator validator = new LatvianPersonalCodeValidator();

        if (validator.isValidUpdatedPersonalCode(personalCode)) {
            throw new PersonalCodeException("Cannot extract data from updated Latvian personal codes");
        } else if (!validator.isValidLegacyPersonalCode(personalCode)) {
            throw new PersonalCodeException("Invalid legacy Latvian personal code");
        }
    }
}
