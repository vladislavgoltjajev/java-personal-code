package com.github.vladislavgoltjajev.personalcode.locale.taiwan;

import com.github.vladislavgoltjajev.personalcode.common.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.utility.NumberUtils;

public class TaiwanesePersonalCodeGenerator {

    /**
     * Generates a random Taiwanese personal code.
     *
     * @return Random Taiwanese personal code.
     */
    public String generateRandomPersonalCode() {
        try {
            return generatePersonalCode(TaiwaneseRegion.getRandomRegion(),
                    Gender.getRandomGender());
        } catch (PersonalCodeException e) {
            // Invalid input parameters not possible, so the checked exception will never be thrown.
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a Taiwanese personal code using the given household registration region.
     * The gender is generated randomly.
     *
     * @param region Person's household registration region.
     * @return Taiwanese personal code.
     * @throws PersonalCodeException If the household registration region is null.
     */
    public String generatePersonalCode(TaiwaneseRegion region)
            throws PersonalCodeException {
        return generatePersonalCode(region, Gender.getRandomGender());
    }

    /**
     * Generates a Taiwanese personal code using the given gender.
     * The household registration region is generated randomly.
     *
     * @param gender Person's gender.
     * @return Taiwanese personal code.
     * @throws PersonalCodeException If the gender is null.
     */
    public String generatePersonalCode(Gender gender)
            throws PersonalCodeException {
        return generatePersonalCode(TaiwaneseRegion.getRandomRegion(), gender);
    }

    /**
     * Generates a Taiwanese personal code using the given household registration region and gender.
     * The household registration region is not checked for validity.
     *
     * @param region Person's household registration region.
     * @param gender Person's gender.
     * @return Taiwanese personal code.
     * @throws PersonalCodeException If the gender or household registration region is null.
     */
    public String generatePersonalCode(TaiwaneseRegion region, Gender gender) throws PersonalCodeException {
        return generatePersonalCode(region, gender, false);
    }

    /**
     * Generates a Taiwanese personal code using the given household registration region and gender and checks if
     * the given household registration region is valid.
     *
     * @param region              Person's household registration region.
     * @param gender              Person's gender.
     * @param checkRegionValidity Whether or not to check if the household registration region is deprecated.
     * @return Taiwanese personal code.
     * @throws PersonalCodeException If the gender or household registration region is null or the household
     *                               registration region validity check is enabled and the region is deprecated.
     */
    public String generatePersonalCode(TaiwaneseRegion region, Gender gender, boolean checkRegionValidity)
            throws PersonalCodeException {
        if (region == null) {
            throw new PersonalCodeException("Household registration region must be specified");
        } else if (checkRegionValidity && !region.isIssued()) {
            throw new PersonalCodeException("Deprecated household registration region");
        } else if (gender == null) {
            throw new PersonalCodeException("Gender must be specified");
        }

        String personalCodeWithoutChecksum = region.getCode()
                + TaiwanesePersonalCodeUtils.getGenderIdentifier(gender)
                + NumberUtils.getRandomNumberWithLeadingZeroes(7);
        return personalCodeWithoutChecksum + TaiwanesePersonalCodeUtils.getChecksum(personalCodeWithoutChecksum);
    }
}
