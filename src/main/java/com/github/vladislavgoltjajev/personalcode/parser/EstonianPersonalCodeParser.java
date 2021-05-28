package com.github.vladislavgoltjajev.personalcode.parser;

import com.github.vladislavgoltjajev.personalcode.enums.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.utility.DateUtils;
import com.github.vladislavgoltjajev.personalcode.utility.ThreadUtils;
import com.github.vladislavgoltjajev.personalcode.validator.EstonianPersonalCodeValidator;

import java.time.LocalDate;
import java.time.Period;

public final class EstonianPersonalCodeParser {

    /**
     * Returns a Period object containing the person's age.
     *
     * @throws PersonalCodeException If the date of birth is in the future.
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
     * Returns the gender defined by the first digit of the personal code.
     * <p>
     * 1, 3, 5 - male.
     * 2, 4, 6 - female.
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
     * Digits 2 through 7 of the personal code show the person's birth date in the format yyddMM.
     * Using the first digit, it is possible to acquire the person's full year of birth.
     * <p>
     * 1, 2 - years 1800-1899.
     * 3, 4 - years 1900-1999.
     * 5, 6 - years 2000-2099.
     */
    public LocalDate getDateOfBirth(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
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

        return LocalDate.parse(dateString, DateUtils.DATE_FORMATTER);
    }

    public int getBirthOrderNumber(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
        return Integer.parseInt(personalCode.substring(7, 10));
    }

    /**
     * Returns the gender identifier (first digit) from the personal code.
     */
    private int getGenderIdentifier(String personalCode) {
        return Character.getNumericValue(personalCode.charAt(0));
    }

    private void validatePersonalCode(String personalCode) throws PersonalCodeException {
        if (!ThreadUtils.stackContainsClass(EstonianPersonalCodeValidator.class)
                && !new EstonianPersonalCodeValidator().isValid(personalCode)) {
            throw new PersonalCodeException("Invalid Estonian personal code");
        }
    }
}
