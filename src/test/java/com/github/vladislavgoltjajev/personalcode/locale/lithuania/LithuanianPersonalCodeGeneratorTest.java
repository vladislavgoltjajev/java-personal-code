package com.github.vladislavgoltjajev.personalcode.locale.lithuania;

import com.github.vladislavgoltjajev.personalcode.common.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LithuanianPersonalCodeGeneratorTest {

    LithuanianPersonalCodeGenerator generator;
    LithuanianPersonalCodeValidator validator;

    @BeforeEach
    void setUp() {
        generator = new LithuanianPersonalCodeGenerator();
        validator = new LithuanianPersonalCodeValidator();
    }

    @Test
    void generateRandomPersonalCode() {
        for (int i = 0; i < 1000; i++) {
            String personalCode = generator.generateRandomPersonalCode();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithGender() throws PersonalCodeException {
        for (int i = 0; i < 1000; i++) {
            Gender gender = Gender.getRandomGender();
            String personalCode = generator.generatePersonalCode(gender);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithGenderAndDateOfBirth() throws PersonalCodeException {
        for (int i = 0; i < 1000; i++) {
            Gender gender = Gender.getRandomGender();
            LocalDate dateOfBirth = LithuanianPersonalCodeUtils.getRandomDateOfBirth();
            String personalCode = generator.generatePersonalCode(gender, dateOfBirth);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithGenderAndDateOfBirthAndBirthOrderNumber() throws PersonalCodeException {
        for (int i = 0; i < 1000; i++) {
            Gender gender = Gender.getRandomGender();
            LocalDate dateOfBirth = LithuanianPersonalCodeUtils.getRandomDateOfBirth();
            int birthOrderNumber = LithuanianPersonalCodeUtils.getRandomBirthOrderNumber();
            String personalCode = generator.generatePersonalCode(gender, dateOfBirth, birthOrderNumber);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithInvalidParameters() {
        assertThatThrownBy(() -> generator.generatePersonalCode(null))
                .isInstanceOf(PersonalCodeException.class);
        assertThatThrownBy(() -> generator.generatePersonalCode(null, LocalDate.of(2000, 1, 1)))
                .isInstanceOf(PersonalCodeException.class);
        assertThatThrownBy(() -> generator.generatePersonalCode(Gender.MALE, null))
                .isInstanceOf(PersonalCodeException.class);
        assertThatThrownBy(() -> generator.generatePersonalCode(Gender.MALE, LocalDate.of(2000, 1, 1), -1))
                .isInstanceOf(PersonalCodeException.class);
        assertThatThrownBy(() -> generator.generatePersonalCode(Gender.MALE, LocalDate.of(2000, 1, 1), 1000))
                .isInstanceOf(PersonalCodeException.class);
    }
}