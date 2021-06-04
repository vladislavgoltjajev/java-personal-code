package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

public final class LatvianPersonalCodeValidator {

    /**
     * Checks if the Latvian personal code (both legacy and updated) is valid.
     *
     * @param personalCode Latvian personal code.
     * @return Whether or not the Latvian personal code is valid.
     */
    public boolean isValid(String personalCode) {
        return isValidUpdatedPersonalCode(personalCode) || isValidLegacyPersonalCode(personalCode);
    }

    /**
     * Checks if the updated Latvian personal code is valid.
     *
     * @param personalCode Updated Latvian personal code.
     * @return Whether or not the updated Latvian personal code is valid.
     */
    public boolean isValidUpdatedPersonalCode(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(LatvianPersonalCodeConstants.LATVIAN_PERSONAL_CODE_REGEX);
    }

    /**
     * Checks if the legacy Latvian personal code is valid.
     *
     * @param personalCode Legacy Latvian personal code.
     * @return Whether or not the legacy Latvian personal code is valid.
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
     * Does not check the personal code's integrity and the validity of the embedded data.
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
