package com.github.vladislavgoltjajev.personalcode.locale.luxembourg;

import com.github.vladislavgoltjajev.personalcode.utility.DateUtils;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

final class LuxembourgishPersonalCodeUtils {

    /**
     * Calculates the Luxembourgish personal code checksum.
     * The checksum is a combination of the Luhn and Verhoeff algorithm checksums.
     *
     * @param personalCode Luxembourgish personal code without the checksum.
     * @return Checksum.
     */
    static String getChecksum(String personalCode) {
        return "" + getLuhnChecksum(personalCode) + getVerhoeffChecksum(personalCode);
    }

    /**
     * Generates a random date between the earliest (01.01.1800) and latest (31.12.2099) possible dates of birth.
     *
     * @return Random date of birth.
     */
    static LocalDate getRandomDateOfBirth() {
        return DateUtils.getRandomDate(LuxembourgishPersonalCodeConstants.MINIMUM_DATE, LuxembourgishPersonalCodeConstants.MAXIMUM_DATE);
    }

    /**
     * Generates a random birth order number (0-999).
     *
     * @return Random birth order number.
     */
    static int getRandomBirthOrderNumber() {
        return new Random().nextInt(1000);
    }

    private static int getLuhnChecksum(String personalCode) {
        int[] digits = getDigitArray(personalCode);

        for (int i = digits.length - 1; i >= 0; i -= 2) {
            digits[i] += digits[i];

            if (digits[i] >= 10) {
                digits[i] = digits[i] - 9;
            }
        }

        int sum = 0;

        for (int digit : digits) {
            sum += digit;
        }

        return sum * 9 % 10;
    }

    private static int getVerhoeffChecksum(String personalCode) {
        int[][] multiplicationTable = new int[][]{
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 0, 6, 7, 8, 9, 5},
                {2, 3, 4, 0, 1, 7, 8, 9, 5, 6},
                {3, 4, 0, 1, 2, 8, 9, 5, 6, 7},
                {4, 0, 1, 2, 3, 9, 5, 6, 7, 8},
                {5, 9, 8, 7, 6, 0, 4, 3, 2, 1},
                {6, 5, 9, 8, 7, 1, 0, 4, 3, 2},
                {7, 6, 5, 9, 8, 2, 1, 0, 4, 3},
                {8, 7, 6, 5, 9, 3, 2, 1, 0, 4},
                {9, 8, 7, 6, 5, 4, 3, 2, 1, 0}
        };
        int[][] permutationTable = new int[][]{
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 5, 7, 6, 2, 8, 3, 0, 9, 4},
                {5, 8, 0, 3, 7, 9, 6, 1, 4, 2},
                {8, 9, 1, 6, 0, 4, 3, 5, 2, 7},
                {9, 4, 5, 3, 1, 2, 6, 8, 7, 0},
                {4, 2, 8, 6, 5, 7, 3, 9, 0, 1},
                {2, 7, 9, 3, 8, 0, 6, 4, 1, 5},
                {7, 0, 4, 6, 9, 1, 3, 2, 5, 8}
        };
        int[] inverseTable = {0, 4, 3, 2, 1, 5, 6, 7, 8, 9};
        int checksum = 0;
        int[] reversedDigits = getReversedDigitArray(getDigitArray(personalCode));

        for (int i = 0; i < reversedDigits.length; i++) {
            checksum = multiplicationTable[checksum][permutationTable[((i + 1) % 8)][reversedDigits[i]]];
        }

        return inverseTable[checksum];
    }

    private static int[] getDigitArray(String personalCode) {
        return Stream.of(personalCode
                .substring(0, 11)
                .split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int[] getReversedDigitArray(int[] digits) {
        int[] reversed = new int[digits.length];

        for (int i = 0; i < digits.length; i++) {
            reversed[i] = digits[digits.length - (i + 1)];
        }

        return reversed;
    }

    private LuxembourgishPersonalCodeUtils() {
    }
}
