package com.github.vladislavgoltjajev.personalcode.locale.uae;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

public class EmiratiPersonalCodeParser {

    /**
     * Returns the person's birth year (digits 4-7).
     *
     * @param personalCode Emirati personal code.
     * @return Birth year.
     * @throws PersonalCodeException If the Emirati personal code is invalid.
     */
    public int getBirthYear(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
        return Integer.parseInt(personalCode.split("-")[1]);
    }

    private void validatePersonalCode(String personalCode) throws PersonalCodeException {
        if (!new EmiratiPersonalCodeValidator().isValid(personalCode)) {
            throw new PersonalCodeException("Invalid Emirati personal code");
        }
    }
}
