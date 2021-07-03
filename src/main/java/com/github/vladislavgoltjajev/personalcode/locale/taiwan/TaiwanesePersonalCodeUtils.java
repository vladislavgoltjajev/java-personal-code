package com.github.vladislavgoltjajev.personalcode.locale.taiwan;

import com.github.vladislavgoltjajev.personalcode.common.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

import java.util.stream.Stream;

final class TaiwanesePersonalCodeUtils {

    static int getChecksum(String personalCode) {
        try {
            TaiwaneseRegion region = getHouseholdRegistrationRegion(personalCode);
            personalCode = personalCode.replaceAll("^[A-Z]", String.valueOf(region.getValue()));
        } catch (PersonalCodeException e) {
            throw new RuntimeException(e);
        }

        int[] digits = Stream.of(personalCode
                .substring(0, 9)
                .split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sum = 0;
        int[] multipliers = {1, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        for (int i = 0; i < digits.length; i++) {
            sum += digits[i] * multipliers[i];
        }

        return ((sum % 10 == 0) ? sum : ((sum / 10 + 1) * 10)) - sum;
    }

    static int getGenderIdentifier(Gender gender) {
        return Gender.MALE.equals(gender) ? 1 : 2;
    }

    static TaiwaneseRegion getHouseholdRegistrationRegion(String personalCode) throws PersonalCodeException {
        String regionCode = String.valueOf(personalCode.charAt(0));
        return TaiwaneseRegion.findByCode(regionCode);
    }

    private TaiwanesePersonalCodeUtils() {
    }
}
