package com.github.vladislavgoltjajev.personalcode.locale.estonia;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

public final class EstonianPersonalCodeValidator {

    /**
     * Checks if the Estonian personal code conforms to the correct format and contains valid data.
     *
     * @param personalCode Estonian personal code.
     * @return Whether or not the Estonian personal code conforms to the correct format and contains valid data.
     */
    public boolean isValid(String personalCode) {
        if (!isFormatValid(personalCode)) {
            return false;
        }

        try {
            new EstonianPersonalCodeParser().getDateOfBirth(personalCode, false);
        } catch (PersonalCodeException e) {
            return false;
        }

        int checksum = Character.getNumericValue(personalCode.charAt(personalCode.length() - 1));
        return checksum == EstonianPersonalCodeUtils.calculateChecksum(personalCode);
    }

    /**
     * Checks if the Estonian personal code conforms to the correct format.
     * Does not check the validity of the data contained in the personal code.
     *
     * @param personalCode Estonian personal code.
     * @return Whether or not the Estonian personal code conforms to the correct format.
     */
    public boolean isFormatValid(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(EstonianPersonalCodeConstants.ESTONIAN_PERSONAL_CODE_REGEX);
    }
}
