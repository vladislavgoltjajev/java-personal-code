package com.github.vladislavgoltjajev.personalcode.locale.estonia;

import com.github.vladislavgoltjajev.personalcode.common.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class EstonianPersonalCodeParser {

    /**
     * Returns the person's gender.
     * The gender is defined by the first digit of the personal code.
     * 1, 3, 5 - male.
     * 2, 4, 6 - female.
     *
     * @param personalCode Estonian personal code.
     * @return Gender.
     * @throws PersonalCodeException If the Estonian personal code is invalid.
     */
    public Gender getGender(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
        switch (getGenderIdentifier(personalCode)) {
            case 1:
            case 3:
            case 5:
                return Gender.MALE;
            default:
                return Gender.FEMALE;
        }
    }

    /**
     * Returns the person's date of birth.
     * Digits 2 through 7 of the Estonian personal code show the person's date of birth in the format YYMMDD.
     * Using the gender identifier (first digit), it is possible to acquire the person's full year of birth.
     * 1, 2 - years 1800-1899.
     * 3, 4 - years 1900-1999.
     * 5, 6 - years 2000-2099.
     *
     * @param personalCode Estonian personal code.
     * @return Date of birth.
     * @throws PersonalCodeException If the Estonian personal code or the date of birth are invalid.
     */
    public LocalDate getDateOfBirth(String personalCode) throws PersonalCodeException {
        return getDateOfBirth(personalCode, true);
    }

    LocalDate getDateOfBirth(String personalCode, boolean validate) throws PersonalCodeException {
        if (validate) {
            validatePersonalCode(personalCode);
        }

        String dateString = personalCode.substring(1, 7);

        switch (getGenderIdentifier(personalCode)) {
            case 1:
            case 2:
                dateString = "18" + dateString;
                break;
            case 3:
            case 4:
                dateString = "19" + dateString;
                break;
            default:
                dateString = "20" + dateString;
        }

        try {
            return LocalDate.parse(dateString, EstonianPersonalCodeConstants.DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new PersonalCodeException(e.getMessage());
        }
    }

    /**
     * Returns the person's age.
     *
     * @param personalCode Estonian personal code.
     * @return Period object containing the person's age.
     * @throws PersonalCodeException If the Estonian personal code is invalid or the date of birth is in the future.
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
     * Returns the person's birth order number (digits 8-10).
     *
     * @param personalCode Estonian personal code.
     * @return Birth order number.
     * @throws PersonalCodeException If the Estonian personal code is invalid.
     */
    public int getBirthOrderNumber(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
        return Integer.parseInt(personalCode.substring(7, 10));
    }

    /**
     * Returns the gender identifier (first digit) in the Estonian personal code.
     *
     * @param personalCode Estonian personal code.
     * @return Gender identifier.
     */
    private int getGenderIdentifier(String personalCode) {
        return Character.getNumericValue(personalCode.charAt(0));
    }

    private void validatePersonalCode(String personalCode) throws PersonalCodeException {
        if (!new EstonianPersonalCodeValidator().isValid(personalCode)) {
            throw new PersonalCodeException("Invalid Estonian personal code");
        }
    }
}
