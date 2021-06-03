package com.github.vladislavgoltjajev.personalcode.locale.latvia;

import org.junit.jupiter.api.Test;

class LatvianPersonalCodeGeneratorTest {

    @Test
    void generateRandomPersonalCode() {
        LatvianPersonalCodeGenerator generator = new LatvianPersonalCodeGenerator();

        for (int i = 0; i < 100; i++) {
            String randomPersonalCode = generator.generateRandomPersonalCode();
            System.out.println(randomPersonalCode);
        }
    }

    @Test
    void generateRandomLegacyPersonalCode() {
        LatvianPersonalCodeGenerator generator = new LatvianPersonalCodeGenerator();

        for (int i = 0; i < 100; i++) {
            String randomPersonalCode = generator.generateRandomLegacyPersonalCode();
            System.out.println(randomPersonalCode);
        }
    }
}