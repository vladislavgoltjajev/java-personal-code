package com.github.vladislavgoltjajev.personalcode.locale.india;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IndianPersonalCodeGeneratorTest {

    private IndianPersonalCodeGenerator generator;
    private IndianPersonalCodeValidator validator;

    @BeforeEach
    void setUp() {
        generator = new IndianPersonalCodeGenerator();
        validator = new IndianPersonalCodeValidator();
    }

    @Test
    void generatePersonalCode() {
        for (int i = 0; i < 1000; i++) {
            String personalCode = generator.generatePersonalCode();
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }
}