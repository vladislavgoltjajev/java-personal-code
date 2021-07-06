package com.github.vladislavgoltjajev.personalcode.locale.taiwan;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

public final class TaiwanesePersonalCodeValidator {

    /**
     * Checks if the Taiwanese personal code is valid.
     *
     * @param personalCode Taiwanese personal code.
     * @return Whether or not the Taiwanese personal code is valid.
     */
    public boolean isValid(String personalCode) {
        if (!isValidFormat(personalCode)) {
            return false;
        }

        int checksum = Character.getNumericValue(personalCode.charAt(personalCode.length() - 1));

        try {
            return checksum == TaiwanesePersonalCodeUtils.getChecksum(personalCode);
        } catch (PersonalCodeException e) {
            return false;
        }
    }

    /**
     * Checks if the Taiwanese personal code conforms to the correct format.
     * Does not check the integrity of the personal code.
     *
     * @param personalCode Taiwanese personal code.
     * @return Whether or not the Taiwanese personal code conforms to the correct format.
     */
    public boolean isValidFormat(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(TaiwanesePersonalCodeConstants.PERSONAL_CODE_REGEX);
    }
}
