package com.github.vladislavgoltjajev.personalcode.locale.taiwan;

import com.github.vladislavgoltjajev.personalcode.common.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TaiwanesePersonalCodeParserTest {

    private TaiwanesePersonalCodeParser parser;

    @BeforeEach
    void setUp() {
        parser = new TaiwanesePersonalCodeParser();
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {
            "test",
            "123",
            "CC209729139",
            "I339460652",
            "1187774969",
            "R1721-92293"
    })
    void parseInvalidPersonalCode(String personalCode) {
        assertThatThrownBy(() -> parser.getHouseholdRegistrationRegion(personalCode))
                .isInstanceOf(PersonalCodeException.class);
    }

    @ParameterizedTest
    @CsvSource({
            "A137513256,TAIPEI_CITY",
            "A189343933,TAIPEI_CITY",
            "C209729139,KEELUNG_CITY",
            "I139460652,CHIAYI_CITY",
            "Y187774969,YANGMINSHAN_MANAGEMENT_BUREAU",
            "R172192293,TAINAN_COUNTY",
            "J151594849,HSINCHU_COUNTY",
            "Y101172711,YANGMINSHAN_MANAGEMENT_BUREAU"
    })
    void getHouseholdRegistrationRegion(String personalCode, TaiwaneseRegion expectedRegion) throws PersonalCodeException {
        TaiwaneseRegion region = parser.getHouseholdRegistrationRegion(personalCode);
        assertThat(region).isEqualTo(expectedRegion);
    }

    @ParameterizedTest
    @CsvSource({
            "A137513256,MALE",
            "A189343933,MALE",
            "C209729139,FEMALE",
            "I139460652,MALE",
            "Y187774969,MALE",
            "R172192293,MALE",
            "J151594849,MALE",
            "Y101172711,MALE"
    })
    void getGender(String personalCode, Gender expectedGender) throws PersonalCodeException {
        Gender gender = parser.getGender(personalCode);
        assertThat(gender).isEqualTo(expectedGender);
    }
}