package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LatvianPersonalCodeGeneratorTest {

    private LatvianPersonalCodeGenerator generator;
    private LatvianPersonalCodeValidator validator;

    @BeforeEach
    void setUp() {
        generator = new LatvianPersonalCodeGenerator();
        validator = new LatvianPersonalCodeValidator();
    }

    @Test
    void generateRandomPersonalCode() {
        for (int i = 0; i < 10000; i++) {
            String personalCode = generator.generateRandomPersonalCode();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generateRandomUpdatedPersonalCode() {
        for (int i = 0; i < 10000; i++) {
            String personalCode = generator.generateRandomUpdatedPersonalCode();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generateRandomLegacyPersonalCode() {
        for (int i = 0; i < 10000; i++) {
            String personalCode = generator.generateRandomLegacyPersonalCode();
            assertThat(validator.isLegacyFormatValid(personalCode)).isTrue();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generateLegacyPersonalCode() throws PersonalCodeException {
        for (int i = 0; i < 10000; i++) {
            LocalDate dateOfBirth = LatvianPersonalCodeUtils.getRandomDateOfBirth();
            String personalCode = generator.generateLegacyPersonalCode(dateOfBirth);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generateLegacyPersonalCodeWithBirthOrderNumber() throws PersonalCodeException {
        for (int i = 0; i < 10000; i++) {
            LocalDate dateOfBirth = LatvianPersonalCodeUtils.getRandomDateOfBirth();
            int birthOrderNumber = LatvianPersonalCodeUtils.getRandomBirthOrderNumber();
            String personalCode = generator.generateLegacyPersonalCode(dateOfBirth, birthOrderNumber);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }
}