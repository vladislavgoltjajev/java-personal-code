package com.github.vladislavgoltjajev.personalcode.locale.estonia;

import com.github.vladislavgoltjajev.personalcode.enums.Gender;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.stream.Stream;

final class EstonianPersonalCodeUtils {

    /**
     * Calculates the Estonian personal code checksum.
     * The checksum (last digit) is calculated by multiplying the first 10 digits of the personal code by
     * the corresponding number in an array of multipliers [1, 2, 3, 4, 5, 6, 7, 8, 9, 1],
     * summing up each result and reducing the sum modulo 11.
     * If the resulting checksum is 10, the process is repeated with a different array of multipliers
     * [3, 4, 5, 6, 7, 8, 9, 1, 2, 3].
     * If the resulting checksum is 10 again, the person's checksum is set to 0.
     *
     * @param personalCode Estonian personal code.
     * @return Calculated checksum.
     */
    static int calculateChecksum(String personalCode) {
        int[] numberArray = Stream.of(personalCode
                .substring(0, 10)
                .split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sum = 0;
        int[] multipliers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};

        for (int i = 0; i < numberArray.length; i++) {
            sum += numberArray[i] * multipliers[i];
        }

        int checksum = sum % 11;

        if (checksum == 10) {
            sum = 0;
            multipliers = new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2, 3};

            for (int i = 0; i < numberArray.length; i++) {
                sum += numberArray[i] * multipliers[i];
            }

            checksum = sum % 11;

            if (checksum == 10) {
                return 0;
            }
        }

        return checksum;
    }

    /**
     * Calculates the gender identifier for the given person's gender and date of birth.
     * Male:
     * years 1800-1899 - 1
     * years 1900-1999 - 3
     * years 2000-2099 - 5
     * Female:
     * years 1800-1899 - 2
     * years 1900-1999 - 4
     * years 2000-2099 - 6
     *
     * @param gender      Person's gender.
     * @param dateOfBirth Person's date of birth.
     * @return Gender identifier.
     */
    static int calculateGenderIdentifier(Gender gender, LocalDate dateOfBirth) {
        int birthYear = dateOfBirth.getYear();
        boolean isMale = Gender.MALE.equals(gender);

        if (birthYear >= 1800 && birthYear <= 1899) {
            return isMale ? 1 : 2;
        } else if (birthYear >= 1900 && birthYear <= 1999) {
            return isMale ? 3 : 4;
        }

        return isMale ? 5 : 6;
    }

    static Gender getRandomGender() {
        return new Random().nextInt(2) == 0 ? Gender.MALE : Gender.FEMALE;
    }

    /**
     * Generates a random date between the earliest (01.01.1800) and latest (31.12.2099) possible birth dates.
     *
     * @return Random date of birth.
     */
    static LocalDate getRandomDateOfBirth() {
        int daysBetween = (int) ChronoUnit.DAYS.between(EstonianPersonalCodeConstants.MINIMUM_DATE, EstonianPersonalCodeConstants.MAXIMUM_DATE);
        return EstonianPersonalCodeConstants.MINIMUM_DATE.plusDays(new Random().nextInt(daysBetween + 1));
    }

    /**
     * Generates a random birth order number (0-999).
     *
     * @return Random birth order number.
     */
    static int getRandomBirthOrderNumber() {
        return new Random().nextInt(1000);
    }

    private EstonianPersonalCodeUtils() {
    }
}
