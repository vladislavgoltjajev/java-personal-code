package com.github.vladislavgoltjajev.personalcode.locale.uae;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmiratiPersonalCodeGeneratorTest {

    private EmiratiPersonalCodeGenerator generator;
    private EmiratiPersonalCodeValidator validator;

    @BeforeEach
    void setUp() {
        generator = new EmiratiPersonalCodeGenerator();
        validator = new EmiratiPersonalCodeValidator();
    }

    @Test
    void generateRandomPersonalCode() {
        for (int i = 0; i < 1000; i++) {
            String personalCode = generator.generateRandomPersonalCode();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithInvalidParameters() {
        assertThatThrownBy(() -> generator.generatePersonalCode(null))
                .isInstanceOf(PersonalCodeException.class);
    }
}