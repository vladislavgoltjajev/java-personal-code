package com.github.vladislavgoltjajev.personalcode.locale.estonia;

import com.github.vladislavgoltjajev.personalcode.enums.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class EstonianPersonalCodeGeneratorTest {

    @Test
    void generateRandomPersonalCode() {
        EstonianPersonalCodeGenerator generator = new EstonianPersonalCodeGenerator();
        EstonianPersonalCodeValidator validator = new EstonianPersonalCodeValidator();

        for (int i = 0; i < 10000; i++) {
            String personalCode = generator.generateRandomPersonalCode();
            System.out.println(personalCode);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCode() throws PersonalCodeException {
        EstonianPersonalCodeGenerator generator = new EstonianPersonalCodeGenerator();
        EstonianPersonalCodeValidator validator = new EstonianPersonalCodeValidator();

        for (int i = 0; i < 10000; i++) {
            Gender gender = EstonianPersonalCodeUtils.getRandomGender();
            LocalDate dateOfBirth = EstonianPersonalCodeUtils.getRandomDateOfBirth();
            String personalCode = generator.generatePersonalCode(gender, dateOfBirth);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithBirthOrderNumber() throws PersonalCodeException {
        EstonianPersonalCodeGenerator generator = new EstonianPersonalCodeGenerator();
        EstonianPersonalCodeValidator validator = new EstonianPersonalCodeValidator();

        for (int i = 0; i < 10000; i++) {
            Gender gender = EstonianPersonalCodeUtils.getRandomGender();
            LocalDate dateOfBirth = EstonianPersonalCodeUtils.getRandomDateOfBirth();
            int birthOrderNumber = EstonianPersonalCodeUtils.getRandomBirthOrderNumber();
            String personalCode = generator.generatePersonalCode(gender, dateOfBirth, birthOrderNumber);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }
}