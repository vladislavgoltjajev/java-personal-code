package com.github.vladislavgoltjajev.personalcode.locale.lithuania;

import com.github.vladislavgoltjajev.personalcode.common.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.utility.DateUtils;
import com.github.vladislavgoltjajev.personalcode.utility.NumberUtils;

import java.time.LocalDate;

public final class LithuanianPersonalCodeGenerator {

    /**
     * Generates a random Lithuanian personal code.
     *
     * @return Random Lithuanian personal code.
     */
    public String generateRandomPersonalCode() {
        try {
            return generatePersonalCode(Gender.getRandomGender(),
                    LithuanianPersonalCodeUtils.getRandomDateOfBirth(),
                    LithuanianPersonalCodeUtils.getRandomBirthOrderNumber());
        } catch (PersonalCodeException e) {
            // Invalid input parameters not possible, so the checked exception will never be thrown.
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a Lithuanian personal code using the given gender.
     * The date of birth and birth order number are generated randomly.
     *
     * @param gender Person's gender.
     * @return Lithuanian personal code.
     * @throws PersonalCodeException If the gender is null.
     */
    public String generatePersonalCode(Gender gender) throws PersonalCodeException {
        return generatePersonalCode(gender,
                LithuanianPersonalCodeUtils.getRandomDateOfBirth(),
                LithuanianPersonalCodeUtils.getRandomBirthOrderNumber());
    }

    /**
     * Generates a Lithuanian personal code using the given gender and date of birth.
     * The birth order number is generated randomly.
     *
     * @param gender      Person's gender.
     * @param dateOfBirth Person's date of birth.
     * @return Lithuanian personal code.
     * @throws PersonalCodeException If the gender or date of birth is null or falls outside the allowed range (01.01.1800-31.12.2099).
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
     * @throws PersonalCodeException If the date of birth is null or if the date of birth or birth order number fall
     *                               outside their allowed ranges (01.01.1800-31.12.2099 and 0-999, respectively).
     */
    public String generatePersonalCode(Gender gender, LocalDate dateOfBirth, int birthOrderNumber)
            throws PersonalCodeException {
        if (gender == null) {
            throw new PersonalCodeException("Gender must be specified");
        } else if (dateOfBirth == null) {
            throw new PersonalCodeException("Date of birth must be specified");
        } else if (dateOfBirth.isBefore(LithuanianPersonalCodeConstants.MINIMUM_DATE)
                || dateOfBirth.isAfter(LithuanianPersonalCodeConstants.MAXIMUM_DATE)) {
            throw new PersonalCodeException(String.format("Date of birth must be between %s and %s",
                    DateUtils.getReadableFormatDate(LithuanianPersonalCodeConstants.MINIMUM_DATE),
                    DateUtils.getReadableFormatDate(LithuanianPersonalCodeConstants.MAXIMUM_DATE)));
        }

        if (birthOrderNumber < 0 || birthOrderNumber > 999) {
            throw new PersonalCodeException("Birth order number must be between 0 and 999");
        }

        String personalCodeWithoutChecksum = LithuanianPersonalCodeUtils.getGenderIdentifier(gender, dateOfBirth)
                + dateOfBirth.format(LithuanianPersonalCodeConstants.DATE_FORMATTER).substring(2)
                + NumberUtils.getNumberWithLeadingZeroes(birthOrderNumber, 3);
        return personalCodeWithoutChecksum + LithuanianPersonalCodeUtils.getChecksum(personalCodeWithoutChecksum);
    }
}
