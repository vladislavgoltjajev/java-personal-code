package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LatvianPersonalCodeGeneratorTest {

    @Test
    void generateRandomPersonalCode() {
        LatvianPersonalCodeGenerator generator = new LatvianPersonalCodeGenerator();
        LatvianPersonalCodeValidator validator = new LatvianPersonalCodeValidator();

        for (int i = 0; i < 10000; i++) {
            String personalCode = generator.generateRandomPersonalCode();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generateRandomLegacyPersonalCode() {
        LatvianPersonalCodeGenerator generator = new LatvianPersonalCodeGenerator();
        LatvianPersonalCodeValidator validator = new LatvianPersonalCodeValidator();

        for (int i = 0; i < 10000; i++) {
            String personalCode = generator.generateRandomLegacyPersonalCode();
            assertThat(validator.isLegacyFormatValid(personalCode)).isTrue();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }
}