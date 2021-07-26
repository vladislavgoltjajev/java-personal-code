package com.github.vladislavgoltjajev.personalcode.locale.taiwan;

import com.github.vladislavgoltjajev.personalcode.common.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

public class TaiwanesePersonalCodeParser {

    /**
     * Returns the person's household registration region (first letter).
     *
     * @param personalCode Taiwanese personal code.
     * @return Person's household registration region.
     * @throws PersonalCodeException If the personal code is invalid.
     */
    public TaiwaneseRegion getHouseholdRegistrationRegion(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
        return TaiwanesePersonalCodeUtils.getHouseholdRegistrationRegion(personalCode);
    }

    /**
     * Returns the person's gender (first digit).
     *
     * @param personalCode Taiwanese personal code.
     * @return Person's gender.
     * @throws PersonalCodeException If the personal code is invalid.
     */
    public Gender getGender(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
        return Character.getNumericValue(personalCode.charAt(1)) == 1 ? Gender.MALE : Gender.FEMALE;
    }

    private void validatePersonalCode(String personalCode) throws PersonalCodeException {
        if (!new TaiwanesePersonalCodeValidator().isValid(personalCode)) {
            throw new PersonalCodeException("Invalid Taiwanese personal code");
        }
    }
}
