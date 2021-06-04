package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

public final class LatvianPersonalCodeValidator {

    /**
     * Checks if the personal code conforms to the correct updated or legacy format and contains valid data.
     *
     * @param personalCode Latvian personal code.
     * @return Whether or not the Latvian personal code conforms to the correct format and contains valid data.
     */
    public boolean isValid(String personalCode) {
        return isValidUpdatedPersonalCode(personalCode) || isValidLegacyPersonalCode(personalCode);
    }

    /**
     * Checks if the personal code conforms to the correct updated format.
     *
     * @param personalCode Latvian personal code.
     * @return Whether or not the Latvian personal code conforms to the correct updated format.
     */
    public boolean isValidUpdatedPersonalCode(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(LatvianPersonalCodeConstants.LATVIAN_PERSONAL_CODE_REGEX);
    }

    /**
     * Checks if the legacy Latvian personal code conforms to the correct format and contains valid data.
     *
     * @param personalCode Legacy Latvian personal code.
     * @return Whether or not the legacy Latvian personal code conforms to the correct format and contains valid data.
     */
    public boolean isValidLegacyPersonalCode(String personalCode) {
        if (!isLegacyFormatValid(personalCode)) {
            return false;
        }

        try {
            new LatvianPersonalCodeParser().getDateOfBirth(personalCode, false);
        } catch (PersonalCodeException e) {
            return false;
        }

        int checksum = Character.getNumericValue(personalCode.charAt(personalCode.length() - 1));
        return checksum == LatvianPersonalCodeUtils.calculateChecksum(personalCode);
    }

    /**
     * Checks if the legacy Latvian personal code conforms to the correct format.
     * Does not check the validity of the data contained in the personal code.
     *
     * @param personalCode Legacy Latvian personal code.
     * @return Whether or not the legacy Latvian personal code conforms to the correct format.
     */
    public boolean isLegacyFormatValid(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(LatvianPersonalCodeConstants.LEGACY_LATVIAN_PERSONAL_CODE_REGEX);
    }
}
