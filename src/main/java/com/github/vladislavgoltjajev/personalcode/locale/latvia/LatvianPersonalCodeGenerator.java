package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.utility.DateUtils;

import java.time.LocalDate;

public final class LatvianPersonalCodeGenerator {

    /**
     * Generates a random Latvian personal code.
     *
     * @return Random Latvian personal code.
     */
    public String generateRandomPersonalCode() {
        String personalCodeWithoutChecksum = "32" + LatvianPersonalCodeUtils.getRandomPersonalIdentifier();
        return personalCodeWithoutChecksum + LatvianPersonalCodeUtils.calculateChecksum(personalCodeWithoutChecksum);
    }

    /**
     * Generates a random legacy format Latvian personal code.
     *
     * @return Random legacy Latvian personal code.
     */
    public String generateRandomLegacyPersonalCode() {
        try {
            return generateLegacyPersonalCode(LatvianPersonalCodeUtils.getRandomDateOfBirth(),
                    LatvianPersonalCodeUtils.getRandomBirthOrderNumber());
        } catch (PersonalCodeException e) {
            // Invalid input parameters not possible, so the checked exception will never be thrown.
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a legacy Latvian personal code using the given gender, date of birth and birth order number.
     *
     * @param dateOfBirth      Person's date of birth.
     * @param birthOrderNumber Person's birth order number.
     * @return Legacy Latvian personal code.
     * @throws PersonalCodeException If the date of birth or birth order number fall outside their allowed ranges (01.01.1800-31.12.2099 and 0-999, respectively).
     */
    public String generateLegacyPersonalCode(LocalDate dateOfBirth, int birthOrderNumber) throws PersonalCodeException {
        if (dateOfBirth == null
                || dateOfBirth.isBefore(LatvianPersonalCodeConstants.MINIMUM_LEGACY_DATE)
                || dateOfBirth.isAfter(LatvianPersonalCodeConstants.MAXIMUM_LEGACY_DATE)) {
            throw new PersonalCodeException(String.format("Date of birth must be between %s and %s",
                    DateUtils.getFormattedDate(LatvianPersonalCodeConstants.MINIMUM_LEGACY_DATE),
                    DateUtils.getFormattedDate(LatvianPersonalCodeConstants.MAXIMUM_LEGACY_DATE)));
        }

        if (birthOrderNumber < 0 || birthOrderNumber > 999) {
            throw new PersonalCodeException("Birth order number must be between 0 and 999");
        }

        String personalCodeWithoutChecksum = dateOfBirth.format(LatvianPersonalCodeConstants.LEGACY_DATE_FORMATTER).substring(0, 6)
                + "-"
                + LatvianPersonalCodeUtils.calculateCenturyIdentifier(dateOfBirth)
                + String.format("%03d", birthOrderNumber);
        return personalCodeWithoutChecksum + LatvianPersonalCodeUtils.calculateChecksum(personalCodeWithoutChecksum);
    }
}
