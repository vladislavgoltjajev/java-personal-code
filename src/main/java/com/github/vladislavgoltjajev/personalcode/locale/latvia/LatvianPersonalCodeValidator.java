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
        return isValidUpdatedFormat(personalCode) && isChecksumValid(personalCode);
    }

    /**
     * Checks if the legacy Latvian personal code is valid.
     *
     * @param personalCode Legacy Latvian personal code.
     * @return Whether or not the legacy Latvian personal code is valid.
     */
    public boolean isValidLegacyPersonalCode(String personalCode) {
        if (!isValidLegacyFormat(personalCode)) {
            return false;
        }

        try {
            new LatvianPersonalCodeParser().getDateOfBirth(personalCode, false);
        } catch (PersonalCodeException e) {
            return false;
        }

        return isChecksumValid(personalCode);
    }

    /**
     * Checks if the specified Latvian personal code conforms to the correct legacy or updated format.
     * Does not check the integrity of the personal code or the validity of the embedded data.
     *
     * @param personalCode Latvian personal code
     * @return Whether or not the Latvian personal code conforms to the correct legacy or updated format.
     */
    public boolean isValidFormat(String personalCode) {
        return isValidUpdatedFormat(personalCode) || isValidLegacyPersonalCode(personalCode);
    }

    /**
     * Checks if the updated Latvian personal code conforms to the correct format.
     * Does not check the integrity of the personal code.
     *
     * @param personalCode Updated Latvian personal code.
     * @return Whether or not the legacy Latvian personal code conforms to the correct format.
     */
    public boolean isValidUpdatedFormat(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(LatvianPersonalCodeConstants.UPDATED_PERSONAL_CODE_REGEX);
    }

    /**
     * Checks if the legacy Latvian personal code conforms to the correct format.
     * Does not check the integrity of the personal code or the validity of the embedded data.
     *
     * @param personalCode Legacy Latvian personal code.
     * @return Whether or not the legacy Latvian personal code conforms to the correct format.
     */
    public boolean isValidLegacyFormat(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(LatvianPersonalCodeConstants.LEGACY_PERSONAL_CODE_REGEX);
    }

    private boolean isChecksumValid(String personalCode) {
        int checksum = Character.getNumericValue(personalCode.charAt(personalCode.length() - 1));
        return checksum == LatvianPersonalCodeUtils.getChecksum(personalCode);
    }
}
