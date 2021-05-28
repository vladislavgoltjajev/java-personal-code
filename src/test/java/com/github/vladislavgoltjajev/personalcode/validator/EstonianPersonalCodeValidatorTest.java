package com.github.vladislavgoltjajev.personalcode.validator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class EstonianPersonalCodeValidatorTest {

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
    public void validateValidPersonalCode(String personalCode) {
        EstonianPersonalCodeValidator validator = new EstonianPersonalCodeValidator();
        assertThat(validator.isValid(personalCode)).isTrue();
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {
            "37605030291",
            "77605030291",
            "60319113016",
            "99999999999",
            "39912310173",
            "39002310001",
            "50102290005",
            "501022900051"
    })
    public void validateInvalidPersonalCode(String personalCode) {
        EstonianPersonalCodeValidator validator = new EstonianPersonalCodeValidator();
        assertThat(validator.isValid(personalCode)).isFalse();
    }
}