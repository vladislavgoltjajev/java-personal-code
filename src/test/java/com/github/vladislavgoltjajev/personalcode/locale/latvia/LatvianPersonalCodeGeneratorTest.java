package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        for (int i = 0; i < 1000; i++) {
            String personalCode = generator.generateRandomPersonalCode();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generateRandomUpdatedPersonalCode() {
        for (int i = 0; i < 1000; i++) {
            String personalCode = generator.generateRandomUpdatedPersonalCode();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generateRandomLegacyPersonalCode() {
        for (int i = 0; i < 1000; i++) {
            String personalCode = generator.generateRandomLegacyPersonalCode();
            assertThat(validator.isValidLegacyFormat(personalCode)).isTrue();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generateLegacyPersonalCode() throws PersonalCodeException {
        for (int i = 0; i < 1000; i++) {
            LocalDate dateOfBirth = LatvianPersonalCodeUtils.getRandomDateOfBirth();
            String personalCode = generator.generateLegacyPersonalCode(dateOfBirth);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generateLegacyPersonalCodeWithBirthOrderNumber() throws PersonalCodeException {
        for (int i = 0; i < 1000; i++) {
            LocalDate dateOfBirth = LatvianPersonalCodeUtils.getRandomDateOfBirth();
            int birthOrderNumber = LatvianPersonalCodeUtils.getRandomBirthOrderNumber();
            String personalCode = generator.generateLegacyPersonalCode(dateOfBirth, birthOrderNumber);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithInvalidParameters() {
        assertThatThrownBy(() -> generator.generateLegacyPersonalCode(null))
                .isInstanceOf(PersonalCodeException.class);
        assertThatThrownBy(() -> generator.generateLegacyPersonalCode(LocalDate.of(2000, 1, 1), -1))
                .isInstanceOf(PersonalCodeException.class);
        assertThatThrownBy(() -> generator.generateLegacyPersonalCode(LocalDate.of(2000, 1, 1), 1000))
                .isInstanceOf(PersonalCodeException.class);
    }
}