package com.github.vladislavgoltjajev.personalcode.locale.luxembourg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class LuxembourgishPersonalCodeValidatorTest {

    LuxembourgishPersonalCodeValidator validator;

    @BeforeEach
    void setUp() {
        validator = new LuxembourgishPersonalCodeValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2049081577690",
            "2026060593451",
            "2000022900197",
            "1944051267737",
            "2087020155450",
            "1973100283493",
            "1917031977873",
            "2015060607344"
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
            "2087022955450",
            "2015023007386",
            "2001992993451",
            "2015-060607344",
            "20150606073441"
    })
    void validateInvalidPersonalCode(String personalCode) {
        assertThat(validator.isValid(personalCode)).isFalse();
    }
}