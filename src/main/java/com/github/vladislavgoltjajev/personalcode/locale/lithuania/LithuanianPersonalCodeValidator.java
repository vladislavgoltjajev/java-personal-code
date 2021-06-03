package com.github.vladislavgoltjajev.personalcode.locale.lithuania;

public final class LithuanianPersonalCodeValidator {

    /**
     * Checks if the Lithuanian personal code conforms to the correct format and contains valid data.
     *
     * @param personalCode Lithuanian personal code.
     * @return Whether or not the Lithuanian personal code conforms to the correct format and contains valid data.
     */
    public boolean isValid(String personalCode) {
        if (!isFormatValid(personalCode)) {
            return false;
        }

        try {
            new LithuanianPersonalCodeParser().getDateOfBirth(personalCode, false);
            int checksum = Character.getNumericValue(personalCode.charAt(personalCode.length() - 1));
            return checksum == LithuanianPersonalCodeUtils.calculateChecksum(personalCode);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the Lithuanian personal code conforms to the correct format.
     * Does not check the validity of the data contained in the personal code.
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
