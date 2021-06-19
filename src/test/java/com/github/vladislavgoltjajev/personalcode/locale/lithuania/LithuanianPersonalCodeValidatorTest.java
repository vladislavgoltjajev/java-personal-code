package com.github.vladislavgoltjajev.personalcode.locale.lithuania;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class LithuanianPersonalCodeValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "17605130008",
            "29912120004",
            "34503020000",
            "47508030046",
            "50109130003",
            "60302050016",
            "60002290003",
            "39912310174",
            "50002290046"
    })
    void validateValidPersonalCode(String personalCode) {
        LithuanianPersonalCodeValidator validator = new LithuanianPersonalCodeValidator();
        assertThat(validator.isValid(personalCode)).isTrue();
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {
            "123",
            "test",
            "37605030291",
            "77605030291",
            "60319113016",
            "99999999999",
            "39912310173",
            "39002310001",
            "50102290005",
            "501022900051"
    })
    void validateInvalidPersonalCode(String personalCode) {
        LithuanianPersonalCodeValidator validator = new LithuanianPersonalCodeValidator();
        assertThat(validator.isValid(personalCode)).isFalse();
    }
}