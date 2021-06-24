package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class LatvianPersonalCodeValidatorTest {

    LatvianPersonalCodeValidator validator;

    @BeforeEach
    void setUp() {
        validator = new LatvianPersonalCodeValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "180565-05610",
            "061193-06460",
            "280638-19406",
            "290212-29381",
            "211244-14874",
            "290156-11605",
            "060287-06450",
            "140761-19428",
            "120768-18764",
            "32073120115",
            "32019028596",
            "32699402624",
            "32416565491",
            "323691-93794",
            "321412-91101",
            "328926-13925",
            "323106-55350"
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
            "37605030291",
            "77605030291",
            "60319113016",
            "99999999999",
            "999999-99999",
            "39912310173",
            "39002310001",
            "50102290005",
            "501022900051",
            "325-442-49548",
            "335442-49548",
            "315442-49548",
            "290213-29381",
            "180565-05611"
    })
    void validateInvalidPersonalCode(String personalCode) {
        assertThat(validator.isValid(personalCode)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "32073120115",
            "32019028596",
            "32699402624",
            "32416565491",
            "323691-93794",
            "321412-91101",
            "328926-13925",
            "323106-55350"
    })
    void validateValidUpdatedPersonalCode(String personalCode) {
        assertThat(validator.isValidUpdatedPersonalCode(personalCode)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "180565-05610",
            "061193-06460",
            "280638-19406",
            "290212-29381",
            "211244-14874",
            "290156-11605",
            "060287-06450",
            "140761-19428",
            "120768-18764"
    })
    void validateValidLegacyPersonalCode(String personalCode) {
        assertThat(validator.isValidLegacyPersonalCode(personalCode)).isTrue();
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
            "999999-99999",
            "39912310173",
            "39002310001",
            "50102290005",
            "501022900051",
            "325-442-49548",
            "335442-49548",
            "315442-49548",
            "290213-29381",
            "180565-05611",
            "180565-05610",
            "061193-06460",
            "280638-19406",
            "290212-29381",
            "211244-14874",
            "290156-11605",
            "060287-06450",
            "140761-19428",
            "120768-18764"
    })
    void validateInvalidUpdatedPersonalCode(String personalCode) {
        assertThat(validator.isValidUpdatedPersonalCode(personalCode)).isFalse();
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
            "999999-99999",
            "39912310173",
            "39002310001",
            "50102290005",
            "501022900051",
            "325-442-49548",
            "335442-49548",
            "315442-49548",
            "290213-29381",
            "180565-05611",
            "32744759186",
            "322589-41469",
            "329294-80588",
            "32240310721"
    })
    void validateInvalidLegacyPersonalCode(String personalCode) {
        assertThat(validator.isValidLegacyPersonalCode(personalCode)).isFalse();
    }
}