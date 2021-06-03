package com.github.vladislavgoltjajev.personalcode.locale.lithuania;

import com.github.vladislavgoltjajev.personalcode.enums.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

import java.time.LocalDate;
import java.time.Period;

public final class LithuanianPersonalCodeParser {

    /**
     * Returns a Period object containing the person's age.
     *
     * @param personalCode Lithuanian personal code.
     * @return Period object containing the person's age.
     * @throws PersonalCodeException If the personal code is invalid or the date of birth is in the future.
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
     * Returns the person's gender.
     * The gender is defined by the first digit of the personal code.
     * 1, 3, 5 - male.
     * 2, 4, 6 - female.
     *
     * @param personalCode Lithuanian personal code.
     * @return Gender.
     * @throws PersonalCodeException If the Lithuanian personal code is invalid.
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

    public LocalDate getDateOfBirth(String personalCode) throws PersonalCodeException {
        return getDateOfBirth(personalCode, true);
    }

    /**
     * Returns the person's date of birth.
     * Digits 2 through 7 of the Lithuanian personal code show the person's birth date in the format yyddMM.
     * Using the gender identifier (first digit), it is possible to acquire the person's full year of birth.
     * 1, 2 - years 1800-1899.
     * 3, 4 - years 1900-1999.
     * 5, 6 - years 2000-2099.
     *
     * @param personalCode Lithuanian personal code.
     * @param validate     Whether or not to skip the Lithuanian personal code validation.
     * @return Date of birth.
     * @throws PersonalCodeException If the Lithuanian personal code or the date of birth is invalid.
     */
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

        return LocalDate.parse(dateString, LithuanianPersonalCodeConstants.DATE_FORMATTER);
    }

    /**
     * Returns the birth order number (digits 8-10) in the Lithuanian personal code.
     *
     * @param personalCode Lithuanian personal code.
     * @return Birth order number.
     * @throws PersonalCodeException If the Lithuanian personal code is invalid.
     */
    public int getBirthOrderNumber(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
        return Integer.parseInt(personalCode.substring(7, 10));
    }

    /**
     * Returns the gender identifier (first digit) from the Lithuanian personal code.
     *
     * @param personalCode Lithuanian personal code.
     * @return Gender identifier.
     */
    private int getGenderIdentifier(String personalCode) {
        return Character.getNumericValue(personalCode.charAt(0));
    }

    private void validatePersonalCode(String personalCode) throws PersonalCodeException {
        if (!new LithuanianPersonalCodeValidator().isValid(personalCode)) {
            throw new PersonalCodeException("Invalid Lithuanian personal code");
        }
    }
}
