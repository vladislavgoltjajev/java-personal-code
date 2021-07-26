package com.github.vladislavgoltjajev.personalcode.locale.uae;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.utility.ChecksumUtils;
import com.github.vladislavgoltjajev.personalcode.utility.NumberUtils;

import java.time.LocalDate;

public class EmiratiPersonalCodeGenerator {

    /**
     * Generates a random Emirati personal code.
     *
     * @return Emirati personal code.
     */
    public String generateRandomPersonalCode() {
        try {
            return generatePersonalCode(EmiratiPersonalCodeUtils.getRandomYearOfBirth());
        } catch (PersonalCodeException e) {
            // Invalid input parameters not possible, so the checked exception will never be thrown.
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates an Emirati personal code using the given date of birth.
     *
     * @param dateOfBirth Person's date of birth.
     * @return Emirati personal code.
     * @throws PersonalCodeException If the date of birth is null.
     */
    public String generatePersonalCode(LocalDate dateOfBirth) throws PersonalCodeException {
        if (dateOfBirth == null) {
            throw new PersonalCodeException("Date of birth must be specified");
        }

        return generatePersonalCode(dateOfBirth.getYear());
    }

    /**
     * Generates an Emirati personal code using the given birth year.
     *
     * @param birthYear Person's birth year.
     * @return Emirati personal code.
     * @throws PersonalCodeException If the birth year is lower than 0 and higher than 9999.
     */
    public String generatePersonalCode(int birthYear) throws PersonalCodeException {
        if (birthYear < 0 || birthYear > 9999) {
            throw new PersonalCodeException("Invalid birth year");
        }

        String personalCodeWithoutChecksum = "784-" + NumberUtils.getNumberWithLeadingZeroes(birthYear, 4)
                + "-" + NumberUtils.getRandomNumberWithLeadingZeroes(7);
        return personalCodeWithoutChecksum + "-" + ChecksumUtils.getLuhnChecksum(personalCodeWithoutChecksum.replaceAll("-", ""));
    }
}
