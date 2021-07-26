package com.github.vladislavgoltjajev.personalcode.locale.estonia;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

public class EstonianPersonalCodeValidator {

    /**
     * Checks if the Estonian personal code is valid.
     *
     * @param personalCode Estonian personal code.
     * @return Whether or not the Estonian personal code is valid.
     */
    public boolean isValid(String personalCode) {
        if (!isValidFormat(personalCode)) {
            return false;
        }

        try {
            new EstonianPersonalCodeParser().getDateOfBirth(personalCode, false);
        } catch (PersonalCodeException e) {
            return false;
        }

        int checksum = Character.getNumericValue(personalCode.charAt(personalCode.length() - 1));
        return checksum == EstonianPersonalCodeUtils.getChecksum(personalCode.substring(0, 10));
    }

    /**
     * Checks if the Estonian personal code conforms to the correct format.
     * Does not check the integrity of the personal code or the validity of the embedded data.
     *
     * @param personalCode Estonian personal code.
     * @return Whether or not the Estonian personal code conforms to the correct format.
     */
    public boolean isValidFormat(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(EstonianPersonalCodeConstants.PERSONAL_CODE_REGEX);
    }
}
