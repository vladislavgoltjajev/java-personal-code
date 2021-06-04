package com.github.vladislavgoltjajev.personalcode.locale.lithuania;

import com.github.vladislavgoltjajev.personalcode.enums.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LithuanianPersonalCodeGeneratorTest {

    @Test
    void generateRandomPersonalCode() {
        LithuanianPersonalCodeGenerator generator = new LithuanianPersonalCodeGenerator();
        LithuanianPersonalCodeValidator validator = new LithuanianPersonalCodeValidator();

        for (int i = 0; i < 10000; i++) {
            String personalCode = generator.generateRandomPersonalCode();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCode() throws PersonalCodeException {
        LithuanianPersonalCodeGenerator generator = new LithuanianPersonalCodeGenerator();
        LithuanianPersonalCodeValidator validator = new LithuanianPersonalCodeValidator();

        for (int i = 0; i < 10000; i++) {
            Gender gender = LithuanianPersonalCodeUtils.getRandomGender();
            LocalDate dateOfBirth = LithuanianPersonalCodeUtils.getRandomDateOfBirth();
            String personalCode = generator.generatePersonalCode(gender, dateOfBirth);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithBirthOrderNumber() throws PersonalCodeException {
        LithuanianPersonalCodeGenerator generator = new LithuanianPersonalCodeGenerator();
        LithuanianPersonalCodeValidator validator = new LithuanianPersonalCodeValidator();

        for (int i = 0; i < 10000; i++) {
            Gender gender = LithuanianPersonalCodeUtils.getRandomGender();
            LocalDate dateOfBirth = LithuanianPersonalCodeUtils.getRandomDateOfBirth();
            int birthOrderNumber = LithuanianPersonalCodeUtils.getRandomBirthOrderNumber();
            String personalCode = generator.generatePersonalCode(gender, dateOfBirth, birthOrderNumber);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }
}