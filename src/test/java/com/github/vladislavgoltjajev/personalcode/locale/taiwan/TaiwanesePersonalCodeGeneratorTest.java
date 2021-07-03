package com.github.vladislavgoltjajev.personalcode.locale.taiwan;

import com.github.vladislavgoltjajev.personalcode.common.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TaiwanesePersonalCodeGeneratorTest {

    private TaiwanesePersonalCodeGenerator generator;
    private TaiwanesePersonalCodeValidator validator;

    @BeforeEach
    void setUp() {
        generator = new TaiwanesePersonalCodeGenerator();
        validator = new TaiwanesePersonalCodeValidator();
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
    void generatePersonalCodeWithGenderAndRegion() throws PersonalCodeException {
        for (int i = 0; i < 1000; i++) {
            Gender gender = Gender.getRandomGender();
            TaiwaneseRegion region = TaiwaneseRegion.getRandomRegion();
            String personalCode = generator.generatePersonalCode(region, gender);
            assertThat(validator.isValid(personalCode)).isTrue();
        }
    }

    @Test
    void generatePersonalCodeWithValidRegion() throws PersonalCodeException {
        Gender gender = Gender.getRandomGender();
        TaiwaneseRegion region = TaiwaneseRegion.TAIPEI_CITY;
        String personalCode = generator.generatePersonalCode(region, gender, true);
        assertThat(validator.isValid(personalCode)).isTrue();
    }

    @Test
    void generatePersonalCodeWithDeprecatedRegion() {
        Gender gender = Gender.getRandomGender();
        TaiwaneseRegion region = TaiwaneseRegion.YANGMINSHAN_MANAGEMENT_BUREAU;
        assertThatThrownBy(() -> generator.generatePersonalCode(region, gender, true))
                .isInstanceOf(PersonalCodeException.class);
    }

    @Test
    void generatePersonalCodeWithInvalidParameters() {
        assertThatThrownBy(() -> generator.generatePersonalCode((Gender) null))
                .isInstanceOf(PersonalCodeException.class);
        assertThatThrownBy(() -> generator.generatePersonalCode((TaiwaneseRegion) null))
                .isInstanceOf(PersonalCodeException.class);
        assertThatThrownBy(() -> generator.generatePersonalCode(null, Gender.MALE))
                .isInstanceOf(PersonalCodeException.class);
        assertThatThrownBy(() -> generator.generatePersonalCode(TaiwaneseRegion.TAIPEI_CITY, null))
                .isInstanceOf(PersonalCodeException.class);
    }
}