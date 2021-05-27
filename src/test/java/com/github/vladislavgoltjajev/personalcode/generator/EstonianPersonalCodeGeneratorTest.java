package com.github.vladislavgoltjajev.personalcode.generator;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.validator.EstonianPersonalCodeValidator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EstonianPersonalCodeGeneratorTest {

    @Test
    void generateRandomPersonalCode() throws PersonalCodeException {
        EstonianPersonalCodeGenerator generator = new EstonianPersonalCodeGenerator();
        EstonianPersonalCodeValidator validator = new EstonianPersonalCodeValidator();

        for (int i = 0; i < 10000; i++) {
            String randomPersonalCode = generator.generateRandomPersonalCode();
            assertThat(validator.isValid(randomPersonalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCode() {
    }

    @Test
    void testGeneratePersonalCode() {
    }

    @Test
    void calculateControlNumber() {
    }
}