package com.github.vladislavgoltjajev.personalcode.locale.estonia;

import com.github.vladislavgoltjajev.personalcode.enums.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.utility.DateUtils;

import java.time.LocalDate;

public final class EstonianPersonalCodeGenerator {

    /**
     * Generates a random Estonian personal code.
     *
     * @return Random Estonian personal code.
     */
    public String generateRandomPersonalCode() {
        try {
            return generatePersonalCode(EstonianPersonalCodeUtils.getRandomGender(),
                    EstonianPersonalCodeUtils.getRandomDateOfBirth(),
                    EstonianPersonalCodeUtils.getRandomBirthOrderNumber());
        } catch (PersonalCodeException e) {
            // Invalid input parameters not possible, so the checked exception will never be thrown.
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates an Estonian personal code using the given gender.
     * The date of birth and birth order number are generated randomly.
     *
     * @param gender Person's gender.
     * @return Estonian personal code.
     * @throws PersonalCodeException If the gender is null.
     */
    public String generatePersonalCode(Gender gender) throws PersonalCodeException {
        return generatePersonalCode(gender,
                EstonianPersonalCodeUtils.getRandomDateOfBirth(),
                EstonianPersonalCodeUtils.getRandomBirthOrderNumber());
    }

    /**
     * Generates an Estonian personal code using the given gender and date of birth.
     * The birth order number is generated randomly.
     *
     * @param gender      Person's gender.
     * @param dateOfBirth Person's date of birth.
     * @return Estonian personal code.
     * @throws PersonalCodeException If the gender or date of birth is null or falls outside the allowed range (01.01.1800-31.12.2099).
     */
    public String generatePersonalCode(Gender gender, LocalDate dateOfBirth) throws PersonalCodeException {
        return generatePersonalCode(gender, dateOfBirth, EstonianPersonalCodeUtils.getRandomBirthOrderNumber());
    }

    /**
     * Generates an Estonian personal code using the given gender, date of birth and birth order number.
     *
     * @param gender           Person's gender.
     * @param dateOfBirth      Person's date of birth.
     * @param birthOrderNumber Person's birth order number.
     * @return Estonian personal code.
     * @throws PersonalCodeException If the gender or date of birth is null or if the date of birth or birth order number fall
     *                               outside their allowed ranges (01.01.1800-31.12.2099 and 0-999, respectively).
     */
    public String generatePersonalCode(Gender gender, LocalDate dateOfBirth, int birthOrderNumber)
            throws PersonalCodeException {
        if (gender == null) {
            throw new PersonalCodeException("Gender must be specified");
        } else if (dateOfBirth == null) {
            throw new PersonalCodeException("Date of birth must be specified");
        } else if (dateOfBirth.isBefore(EstonianPersonalCodeConstants.MINIMUM_DATE)
                || dateOfBirth.isAfter(EstonianPersonalCodeConstants.MAXIMUM_DATE)) {
            throw new PersonalCodeException(String.format("Date of birth must be between %s and %s",
                    DateUtils.getReadableFormatDate(EstonianPersonalCodeConstants.MINIMUM_DATE),
                    DateUtils.getReadableFormatDate(EstonianPersonalCodeConstants.MAXIMUM_DATE)));
        }

        if (birthOrderNumber < 0 || birthOrderNumber > 999) {
            throw new PersonalCodeException("Birth order number must be between 0 and 999");
        }

        String personalCodeWithoutChecksum = EstonianPersonalCodeUtils.getGenderIdentifier(gender, dateOfBirth)
                + dateOfBirth.format(EstonianPersonalCodeConstants.DATE_FORMATTER).substring(2)
                + String.format("%03d", birthOrderNumber);
        return personalCodeWithoutChecksum + EstonianPersonalCodeUtils.getChecksum(personalCodeWithoutChecksum);
    }
}
