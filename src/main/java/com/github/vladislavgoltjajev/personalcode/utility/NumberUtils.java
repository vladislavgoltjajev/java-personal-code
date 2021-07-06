package com.github.vladislavgoltjajev.personalcode.utility;

import java.util.Random;

public final class NumberUtils {

    public static String getNumberWithLeadingZeroes(int number, int length) {
        return String.format("%0" + length + "d", number);
    }

    public static String getRandomNumberWithLeadingZeroes(int length) {
        int number = new Random().nextInt(Integer.parseInt("1" + "0".repeat(Math.max(0, length)), 10));
        return getNumberWithLeadingZeroes(number, length);
    }
}
