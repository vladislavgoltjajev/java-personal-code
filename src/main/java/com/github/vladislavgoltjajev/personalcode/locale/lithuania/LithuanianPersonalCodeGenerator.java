package com.github.vladislavgoltjajev.personalcode.locale.lithuania;

import com.github.vladislavgoltjajev.personalcode.enums.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.utility.DateUtils;

import java.time.LocalDate;

public final class LithuanianPersonalCodeGenerator {

    /**
     * Generates a random Lithuanian personal code.
     *
     * @return Random Lithuanian personal code.
     */
    public String generateRandomPersonalCode() {
        try {
            return generatePersonalCode(LithuanianPersonalCodeUtils.getRandomGender(),
                    LithuanianPersonalCodeUtils.getRandomDateOfBirth(),
                    LithuanianPersonalCodeUtils.getRandomBirthOrderNumber());
        } catch (PersonalCodeException e) {
            // Invalid input parameters not possible, so the checked exception will never be thrown.
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a Lithuanian personal code using the given gender and date of birth.
     * The birth order number is assigned randomly.
     *
     * @param gender      Person's gender.
     * @param dateOfBirth Person's date of birth.
     * @return Lithuanian personal code.
     * @throws PersonalCodeException If the date of birth falls outside the allowed range (01.01.1800-31.12.2099).
     */
    public String generatePersonalCode(Gender gender, LocalDate dateOfBirth) throws PersonalCodeException {
        return generatePersonalCode(gender, dateOfBirth, LithuanianPersonalCodeUtils.getRandomBirthOrderNumber());
    }

    /**
     * Generates a Lithuanian personal code using the given gender, date of birth and birth order number.
     *
     * @param gender           Person's gender.
     * @param dateOfBirth      Person's date of birth.
     * @param birthOrderNumber Person's birth order number.
     * @return Lithuanian personal code.
     * @throws PersonalCodeException If the date of birth or birth order number fall outside their allowed ranges (01.01.1800-31.12.2099 and 0-999, respectively).
     */
    public String generatePersonalCode(Gender gender, LocalDate dateOfBirth, int birthOrderNumber)
            throws PersonalCodeException {
        if (dateOfBirth == null
                || dateOfBirth.isBefore(LithuanianPersonalCodeConstants.MINIMUM_DATE)
                || dateOfBirth.isAfter(LithuanianPersonalCodeConstants.MAXIMUM_DATE)) {
            throw new PersonalCodeException(String.format("Date of birth must be between %s and %s",
                    DateUtils.getFormattedDate(LithuanianPersonalCodeConstants.MINIMUM_DATE),
                    DateUtils.getFormattedDate(LithuanianPersonalCodeConstants.MAXIMUM_DATE)));
        }

        if (birthOrderNumber < 0 || birthOrderNumber > 999) {
            throw new PersonalCodeException("Birth order number must be between 0 and 999");
        }

        String personalCodeWithoutChecksum = LithuanianPersonalCodeUtils.calculateGenderIdentifier(gender, dateOfBirth)
                + dateOfBirth.format(LithuanianPersonalCodeConstants.DATE_FORMATTER).substring(2)
                + String.format("%03d", birthOrderNumber);
        return personalCodeWithoutChecksum + LithuanianPersonalCodeUtils.calculateChecksum(personalCodeWithoutChecksum);
    }
}
