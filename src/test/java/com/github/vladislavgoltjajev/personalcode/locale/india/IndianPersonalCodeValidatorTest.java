package com.github.vladislavgoltjajev.personalcode.locale.india;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class IndianPersonalCodeValidatorTest {

    private IndianPersonalCodeValidator validator ;

    @BeforeEach
    void setUp() {
        validator = new IndianPersonalCodeValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "4904-0054-0750",
            "4626-3979-2940",
            "7041-3249-6745",
            "9185-8655-0944",
            "5370-9799-2998",
            "3791-7122-0416",
            "3158-2191-9741",
            "9778-1311-0799",
            "8598-5666-8481"
    })
    void validateValidPersonalCode(String personalCode) {
        assertThat(validator.isValid(personalCode)).isTrue();
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {
            "123",
            "test",
            "8598-5666-8480",
            "859856668481",
            "8598-5666-84812"
    })
    void validateInvalidPersonalCode(String personalCode) {
        assertThat(validator.isValid(personalCode)).isFalse();
    }
}