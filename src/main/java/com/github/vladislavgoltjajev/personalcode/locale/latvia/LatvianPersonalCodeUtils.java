package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import com.github.vladislavgoltjajev.personalcode.utility.DateUtils;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

final class LatvianPersonalCodeUtils {

    /**
     * Calculates the legacy Latvian personal code checksum.
     * The checksum (last digit) is calculated by multiplying the first 10 digits of the personal code by
     * the corresponding number in an array of multipliers [1, 6, 3, 7, 9, 10, 5, 8, 4, 2],
     * summing up each result, subtracting the sum from 1101 and reducing the result modulo 11 and 10.
     *
     * @param personalCode Latvian personal code.
     * @return Calculated checksum.
     */
    static int calculateChecksum(String personalCode) {
        int[] numberArray = Stream.of(personalCode
                .replace("-", "")
                .substring(0, 10)
                .split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sum = 0;
        int[] multipliers = {1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

        for (int i = 0; i < numberArray.length; i++) {
            sum += numberArray[i] * multipliers[i];
        }

        return (1101 - sum) % 11 % 10;
    }

    /**
     * Calculates the century identifier for the given person's date of birth:
     * years 1800-1899 - 0
     * years 1900-1999 - 1
     * years 2000-2017 - 2
     *
     * @param dateOfBirth Person's date of birth.
     * @return Gender identifier.
     */
    static int calculateCenturyIdentifier(LocalDate dateOfBirth) {
        int birthYear = dateOfBirth.getYear();

        if (birthYear >= 1800 && birthYear <= 1899) {
            return 0;
        } else if (birthYear >= 1900 && birthYear <= 1999) {
            return 1;
        }

        return 2;
    }

    /**
     * Generates a random date between the earliest (01.01.1800) and latest (31.12.2099) possible birth dates.
     *
     * @return Random date of birth.
     */
    static LocalDate getRandomDateOfBirth() {
        return DateUtils.getRandomDate(LatvianPersonalCodeConstants.MINIMUM_LEGACY_DATE, LatvianPersonalCodeConstants.MAXIMUM_LEGACY_DATE);
    }

    /**
     * Generates a random birth order number (0-999).
     *
     * @return Random birth order number.
     */
    static int getRandomBirthOrderNumber() {
        return new Random().nextInt(1000);
    }

    private LatvianPersonalCodeUtils() {
    }
}
