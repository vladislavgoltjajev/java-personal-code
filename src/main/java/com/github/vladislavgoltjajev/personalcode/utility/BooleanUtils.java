package com.github.vladislavgoltjajev.personalcode.utility;

public final class BooleanUtils {

    public static boolean get50PercentChance() {
        return Math.random() > 0.5;
    }

    private BooleanUtils() {
    }
}
