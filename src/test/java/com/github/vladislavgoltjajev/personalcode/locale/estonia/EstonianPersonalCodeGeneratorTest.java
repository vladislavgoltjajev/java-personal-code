package com.github.vladislavgoltjajev.personalcode.locale.estonia;

import com.github.vladislavgoltjajev.personalcode.enums.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class EstonianPersonalCodeGeneratorTest {

    EstonianPersonalCodeGenerator generator;
    EstonianPersonalCodeValidator validator;

    @BeforeEach
    void setUp() {
        generator = new EstonianPersonalCodeGenerator();
        validator = new EstonianPersonalCodeValidator();
    }

    @Test
    void generateRandomPersonalCode() {
        for (int i = 0; i < 10000; i++) {
            String personalCode = generator.generateRandomPersonalCode();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithGender() {
        for (int i = 0; i < 10000; i++) {
            Gender gender = EstonianPersonalCodeUtils.getRandomGender();
            String personalCode = generator.generatePersonalCode(gender);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithGenderAndDateOfBirth() throws PersonalCodeException {
        for (int i = 0; i < 10000; i++) {
            Gender gender = EstonianPersonalCodeUtils.getRandomGender();
            LocalDate dateOfBirth = EstonianPersonalCodeUtils.getRandomDateOfBirth();
            String personalCode = generator.generatePersonalCode(gender, dateOfBirth);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithGenderAndDateOfBirthAndBirthOrderNumber() throws PersonalCodeException {
        for (int i = 0; i < 10000; i++) {
            Gender gender = EstonianPersonalCodeUtils.getRandomGender();
            LocalDate dateOfBirth = EstonianPersonalCodeUtils.getRandomDateOfBirth();
            int birthOrderNumber = EstonianPersonalCodeUtils.getRandomBirthOrderNumber();
            String personalCode = generator.generatePersonalCode(gender, dateOfBirth, birthOrderNumber);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }
}