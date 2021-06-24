package com.github.vladislavgoltjajev.personalcode.locale.luxembourg;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LuxembourgishPersonalCodeGeneratorTest {

    private LuxembourgishPersonalCodeGenerator generator;
    private LuxembourgishPersonalCodeValidator validator;

    @BeforeEach
    void setUp() {
        generator = new LuxembourgishPersonalCodeGenerator();
        validator = new LuxembourgishPersonalCodeValidator();
    }

    @Test
    void generateRandomPersonalCode() {
        for (int i = 0; i < 1000; i++) {
            String personalCode = generator.generateRandomPersonalCode();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithDateOfBirth() throws PersonalCodeException {
        for (int i = 0; i < 1000; i++) {
            LocalDate dateOfBirth = LuxembourgishPersonalCodeUtils.getRandomDateOfBirth();
            String personalCode = generator.generatePersonalCode(dateOfBirth);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithDateOfBirthAndBirthOrderNumber() throws PersonalCodeException {
        for (int i = 0; i < 1000; i++) {
            LocalDate dateOfBirth = LuxembourgishPersonalCodeUtils.getRandomDateOfBirth();
            int birthOrderNumber = LuxembourgishPersonalCodeUtils.getRandomBirthOrderNumber();
            String personalCode = generator.generatePersonalCode(dateOfBirth, birthOrderNumber);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithInvalidParameters() {
        assertThatThrownBy(() -> generator.generatePersonalCode(null))
                .isInstanceOf(PersonalCodeException.class);
        assertThatThrownBy(() -> generator.generatePersonalCode(LocalDate.of(2000, 1, 1), -1))
                .isInstanceOf(PersonalCodeException.class);
        assertThatThrownBy(() -> generator.generatePersonalCode(LocalDate.of(2000, 1, 1), 1000))
                .isInstanceOf(PersonalCodeException.class);
    }
}