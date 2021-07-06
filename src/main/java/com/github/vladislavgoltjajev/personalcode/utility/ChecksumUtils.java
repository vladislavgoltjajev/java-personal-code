package com.github.vladislavgoltjajev.personalcode.utility;

import java.util.stream.Stream;

public final class ChecksumUtils {

    public static int getLuhnChecksum(String personalCode) {
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

    public static int getVerhoeffChecksum(String personalCode) {
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
        int[] reversedDigits = getReversedDigitArray(personalCode);

        for (int i = 0; i < reversedDigits.length; i++) {
            checksum = multiplicationTable[checksum][permutationTable[((i + 1) % 8)][reversedDigits[i]]];
        }

        return inverseTable[checksum];
    }

    private static int[] getDigitArray(String personalCode) {
        return Stream.of(personalCode
                .split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int[] getReversedDigitArray(String personalCode) {
        int[] digits = getDigitArray(personalCode);
        int[] reversedDigits = new int[digits.length];

        for (int i = 0; i < digits.length; i++) {
            reversedDigits[i] = digits[digits.length - (i + 1)];
        }

        return reversedDigits;
    }
}
