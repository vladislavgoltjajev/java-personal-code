package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.utility.BooleanUtils;
import com.github.vladislavgoltjajev.personalcode.utility.DateUtils;
import com.github.vladislavgoltjajev.personalcode.utility.NumberUtils;

import java.time.LocalDate;

public class LatvianPersonalCodeGenerator {

    /**
     * Generates a random Latvian personal code.
     *
     * @return Random Latvian personal code.
     */
    public String generateRandomPersonalCode() {
        return BooleanUtils.get50PercentChance() ? generateRandomUpdatedPersonalCode() : generateRandomLegacyPersonalCode();
    }

    /**
     * Generates a random updated Latvian personal code.
     * May contain a dash between the 6th and 7th digit.
     *
     * @return Random updated Latvian personal code.
     */
    public String generateRandomUpdatedPersonalCode() {
        return generateRandomUpdatedPersonalCode(BooleanUtils.get50PercentChance());
    }

    /**
     * Generates a random updated Latvian personal code.
     *
     * @param addDash Whether or not to add a dash between the 6th and 7th digit.
     * @return Random updated Latvian personal code.
     */
    public String generateRandomUpdatedPersonalCode(boolean addDash) {
        String personalCodeWithoutChecksum = "32" + NumberUtils.getRandomNumberWithLeadingZeroes(8);
        String personalCode = personalCodeWithoutChecksum + LatvianPersonalCodeUtils.getChecksum(personalCodeWithoutChecksum);

        if (addDash) {
            personalCode = personalCode.substring(0, 6) + "-" + personalCode.substring(6);
        }

        return personalCode;
    }

    /**
     * Generates a random legacy Latvian personal code.
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
     * Generates a legacy Latvian personal code using the given date of birth.
     * The birth order number is generated randomly.
     *
     * @param dateOfBirth Person's date of birth.
     * @return Legacy Latvian personal code.
     * @throws PersonalCodeException If the date of birth is null or falls outside the allowed range (01.01.1800-30.06.2017).
     */
    public String generateLegacyPersonalCode(LocalDate dateOfBirth) throws PersonalCodeException {
        return generateLegacyPersonalCode(dateOfBirth, LatvianPersonalCodeUtils.getRandomBirthOrderNumber());
    }

    /**
     * Generates a legacy Latvian personal code using the given date of birth and birth order number.
     *
     * @param dateOfBirth      Person's date of birth.
     * @param birthOrderNumber Person's birth order number.
     * @return Legacy Latvian personal code.
     * @throws PersonalCodeException If the date of birth is null or if the date of birth or birth order number fall
     *                               outside their allowed ranges (01.01.1800-31.12.2099 and 0-999, respectively).
     */
    public String generateLegacyPersonalCode(LocalDate dateOfBirth, int birthOrderNumber) throws PersonalCodeException {
        if (dateOfBirth == null) {
            throw new PersonalCodeException("Date of birth must be specified");
        } else if (dateOfBirth.isBefore(LatvianPersonalCodeConstants.MINIMUM_LEGACY_DATE)
                || dateOfBirth.isAfter(LatvianPersonalCodeConstants.MAXIMUM_LEGACY_DATE)) {
            throw new PersonalCodeException(String.format("Date of birth must be between %s and %s",
                    DateUtils.getReadableFormatDate(LatvianPersonalCodeConstants.MINIMUM_LEGACY_DATE),
                    DateUtils.getReadableFormatDate(LatvianPersonalCodeConstants.MAXIMUM_LEGACY_DATE)));
        }

        if (birthOrderNumber < 0 || birthOrderNumber > 999) {
            throw new PersonalCodeException("Birth order number must be between 0 and 999");
        }

        String dateString = dateOfBirth.format(LatvianPersonalCodeConstants.LEGACY_DATE_FORMATTER);
        String personalCodeWithoutChecksum = dateString.substring(0, 4) + dateString.substring(6)
                + "-"
                + LatvianPersonalCodeUtils.getCenturyIdentifier(dateOfBirth)
                + NumberUtils.getNumberWithLeadingZeroes(birthOrderNumber, 3);
        return personalCodeWithoutChecksum + LatvianPersonalCodeUtils.getChecksum(personalCodeWithoutChecksum);
    }
}
