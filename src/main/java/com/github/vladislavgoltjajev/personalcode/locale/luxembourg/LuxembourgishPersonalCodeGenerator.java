package com.github.vladislavgoltjajev.personalcode.locale.luxembourg;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

import java.time.LocalDate;

public final class LuxembourgishPersonalCodeGenerator {

    /**
     * Generates a random Luxembourgish personal code.
     *
     * @return Random Luxembourgish personal code.
     */
    public String generateRandomPersonalCode() {
        try {
            return generatePersonalCode(LuxembourgishPersonalCodeUtils.getRandomDateOfBirth(),
                    LuxembourgishPersonalCodeUtils.getRandomBirthOrderNumber());
        } catch (PersonalCodeException e) {
            // Invalid input parameters not possible, so the checked exception will never be thrown.
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a Luxembourgish personal code using the given date of birth.
     * The birth order number is generated randomly.
     *
     * @param dateOfBirth Person's date of birth.
     * @return Luxembourgish personal code.
     * @throws PersonalCodeException If the date of birth is null.
     */
    public String generatePersonalCode(LocalDate dateOfBirth) throws PersonalCodeException {
        return generatePersonalCode(dateOfBirth, LuxembourgishPersonalCodeUtils.getRandomBirthOrderNumber());
    }

    /**
     * Generates a Luxembourgish personal code using the given date of birth and birth order number.
     *
     * @param dateOfBirth      Person's date of birth.
     * @param birthOrderNumber Person's birth order number.
     * @return Luxembourgish personal code.
     * @throws PersonalCodeException If the date of birth is null or the birth order number falls outside the allowed range (0-999).
     */
    public String generatePersonalCode(LocalDate dateOfBirth, int birthOrderNumber)
            throws PersonalCodeException {
        if (dateOfBirth == null) {
            throw new PersonalCodeException("Date of birth must be specified");
        }

        if (birthOrderNumber < 0 || birthOrderNumber > 999) {
            throw new PersonalCodeException("Birth order number must be between 0 and 999");
        }

        String personalCodeWithoutChecksum = dateOfBirth.format(LuxembourgishPersonalCodeConstants.DATE_FORMATTER)
                + String.format("%03d", birthOrderNumber);
        return personalCodeWithoutChecksum + LuxembourgishPersonalCodeUtils.getChecksum(personalCodeWithoutChecksum);
    }
}
