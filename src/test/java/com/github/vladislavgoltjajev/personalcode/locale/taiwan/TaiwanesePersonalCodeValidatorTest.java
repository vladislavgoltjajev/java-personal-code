package com.github.vladislavgoltjajev.personalcode.locale.taiwan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TaiwanesePersonalCodeValidatorTest {

    private TaiwanesePersonalCodeValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TaiwanesePersonalCodeValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "A137513256",
            "A189343933",
            "C209729139",
            "I139460652",
            "Y187774969",
            "R172192293",
            "J151594849",
            "Y101172711"
    })
    void validateValidPersonalCode(String personalCode) {
        assertThat(validator.isValid(personalCode)).isTrue();
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {
            "test",
            "123",
            "CC209729139",
            "I339460652",
            "1187774969",
            "R1721-92293"
    })
    void validateInvalidPersonalCode(String personalCode) {
        assertThat(validator.isValid(personalCode)).isFalse();
    }
}