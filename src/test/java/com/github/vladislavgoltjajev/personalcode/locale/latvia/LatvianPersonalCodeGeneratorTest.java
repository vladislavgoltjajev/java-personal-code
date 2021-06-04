package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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

    @Test
    void generateLegacyPersonalCode() throws PersonalCodeException {
        LatvianPersonalCodeGenerator generator = new LatvianPersonalCodeGenerator();
        LatvianPersonalCodeValidator validator = new LatvianPersonalCodeValidator();

        for (int i = 0; i < 10000; i++) {
            LocalDate dateOfBirth = LatvianPersonalCodeUtils.getRandomDateOfBirth();
            String personalCode = generator.generateLegacyPersonalCode(dateOfBirth);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generateLegacyPersonalCodeWithBirthOrderNumber() throws PersonalCodeException {
        LatvianPersonalCodeGenerator generator = new LatvianPersonalCodeGenerator();
        LatvianPersonalCodeValidator validator = new LatvianPersonalCodeValidator();

        for (int i = 0; i < 10000; i++) {
            LocalDate dateOfBirth = LatvianPersonalCodeUtils.getRandomDateOfBirth();
            int birthOrderNumber = LatvianPersonalCodeUtils.getRandomBirthOrderNumber();
            String personalCode = generator.generateLegacyPersonalCode(dateOfBirth, birthOrderNumber);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }
}