package com.github.vladislavgoltjajev.personalcode.locale.uae;

import com.github.vladislavgoltjajev.personalcode.utility.ChecksumUtils;

public class EmiratiPersonalCodeValidator {

    /**
     * Checks if the Emirati personal code is valid.
     *
     * @param personalCode Emirati personal code.
     * @return Whether or not the Emirati personal code is valid.
     */
    public boolean isValid(String personalCode) {
        if (!isValidFormat(personalCode)) {
            return false;
        }

        int checksum = Character.getNumericValue(personalCode.charAt(personalCode.length() - 1));
        return checksum == ChecksumUtils.getLuhnChecksum(personalCode.replaceAll("-", "").substring(0, 14));
    }

    /**
     * Checks if the Emirati personal code conforms to the correct format.
     * Does not check the integrity of the personal code.
     *
     * @param personalCode Emirati personal code.
     * @return Whether or not the Emirati personal code conforms to the correct format.
     */
    public boolean isValidFormat(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(EmiratiPersonalCodeConstants.PERSONAL_CODE_REGEX);
    }
}
