package com.github.vladislavgoltjajev.personalcode.locale.luxembourg;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

public final class LuxembourgishPersonalCodeValidator {

    /**
     * Checks if the Luxembourgish personal code is valid.
     *
     * @param personalCode Luxembourgish personal code.
     * @return Whether or not the Luxembourgish personal code is valid.
     */
    public boolean isValid(String personalCode) {
        if (!isValidFormat(personalCode)) {
            return false;
        }

        try {
            new LuxembourgishPersonalCodeParser().getDateOfBirth(personalCode, false);
        } catch (PersonalCodeException e) {
            return false;
        }

        String checksum = personalCode.substring(personalCode.length() - 2);
        return checksum.equals(LuxembourgishPersonalCodeUtils.getChecksum(personalCode));
    }

    /**
     * Checks if the Luxembourgish personal code conforms to the correct format.
     * Does not check the integrity of the personal code or the validity of the embedded data.
     *
     * @param personalCode Luxembourgish personal code.
     * @return Whether or not the Luxembourgish personal code conforms to the correct format.
     */
    public boolean isValidFormat(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(LuxembourgishPersonalCodeConstants.PERSONAL_CODE_REGEX);
    }
}
