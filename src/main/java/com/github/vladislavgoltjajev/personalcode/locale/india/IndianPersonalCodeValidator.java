package com.github.vladislavgoltjajev.personalcode.locale.india;

import com.github.vladislavgoltjajev.personalcode.utility.ChecksumUtils;

public final class IndianPersonalCodeValidator {

    /**
     * Checks if the Indian personal code is valid.
     *
     * @param personalCode Indian personal code.
     * @return Whether or not the Indian personal code is valid.
     */
    public boolean isValid(String personalCode) {
        if (!isValidFormat(personalCode)) {
            return false;
        }

        int checksum = Character.getNumericValue(personalCode.charAt(personalCode.length() - 1));
        return checksum == ChecksumUtils.getVerhoeffChecksum(personalCode.replaceAll("-", "").substring(0, 11));
    }

    /**
     * Checks if the Indian personal code conforms to the correct format.
     * Does not check the integrity of the personal code.
     *
     * @param personalCode Indian personal code.
     * @return Whether or not the Indian personal code conforms to the correct format.
     */
    public boolean isValidFormat(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(IndianPersonalCodeConstants.PERSONAL_CODE_REGEX);
    }
}
