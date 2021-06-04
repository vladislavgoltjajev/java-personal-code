package com.github.vladislavgoltjajev.personalcode.locale.lithuania;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

public final class LithuanianPersonalCodeValidator {

    /**
     * Checks if the Lithuanian personal code is valid.
     *
     * @param personalCode Lithuanian personal code.
     * @return Whether or not the Lithuanian personal code is valid.
     */
    public boolean isValid(String personalCode) {
        if (!isFormatValid(personalCode)) {
            return false;
        }

        try {
            new LithuanianPersonalCodeParser().getDateOfBirth(personalCode, false);
        } catch (PersonalCodeException e) {
            return false;
        }

        int checksum = Character.getNumericValue(personalCode.charAt(personalCode.length() - 1));
        return checksum == LithuanianPersonalCodeUtils.calculateChecksum(personalCode);
    }

    /**
     * Checks if the Lithuanian personal code conforms to the correct format.
     * Does not check the personal code's integrity and the validity of the embedded data.
     *
     * @param personalCode Lithuanian personal code.
     * @return Whether or not the Lithuanian personal code conforms to the correct format.
     */
    public boolean isFormatValid(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(LithuanianPersonalCodeConstants.LITHUANIAN_PERSONAL_CODE_REGEX);
    }
}
