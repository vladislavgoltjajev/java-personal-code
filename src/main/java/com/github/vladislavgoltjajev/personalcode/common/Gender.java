package com.github.vladislavgoltjajev.personalcode.common;

import com.github.vladislavgoltjajev.personalcode.utility.BooleanUtils;

public enum Gender {

    MALE,
    FEMALE;

    public static Gender getRandomGender() {
        return BooleanUtils.get50PercentChance() ? MALE : FEMALE;
    }
}
