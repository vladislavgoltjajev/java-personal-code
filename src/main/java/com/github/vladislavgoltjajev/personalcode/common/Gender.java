package com.github.vladislavgoltjajev.personalcode.common;

import java.util.Random;

public enum Gender {

    MALE,
    FEMALE;

    public static Gender getRandomGender() {
        return Gender.values()[new Random().nextInt(Gender.values().length)];
    }
}
