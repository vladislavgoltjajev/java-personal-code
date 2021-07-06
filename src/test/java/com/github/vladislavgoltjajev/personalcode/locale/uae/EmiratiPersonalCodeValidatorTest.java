package com.github.vladislavgoltjajev.personalcode.locale.uae;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class EmiratiPersonalCodeValidatorTest {

    private EmiratiPersonalCodeValidator validator;

    @BeforeEach
    void setUp() {
        validator = new EmiratiPersonalCodeValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "784-1919-8064008-9",
            "784-2031-6945294-5",
            "784-2010-4532229-0",
            "784-1935-0284231-8",
            "784-1944-5708207-4",
            "784-2006-6530100-6",
            "784-2014-7323057-7",
            "784-1956-4970294-1"
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
            "784-1980-1234567-9",
            "784-1234-0123456-7",
            "123-1234-0123456-7",
            "123-1234-0123456-77",
            "784198012345679"
    })
    void validateInvalidPersonalCode(String personalCode) {
        assertThat(validator.isValid(personalCode)).isFalse();
    }
}