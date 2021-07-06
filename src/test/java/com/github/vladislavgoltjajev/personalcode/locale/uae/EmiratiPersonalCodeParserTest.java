package com.github.vladislavgoltjajev.personalcode.locale.uae;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmiratiPersonalCodeParserTest {

    private EmiratiPersonalCodeParser parser;

    @BeforeEach
    void setUp() {
        parser = new EmiratiPersonalCodeParser();
    }

    @ParameterizedTest
    @CsvSource({
            "784-1919-8064008-9,1919",
            "784-2031-6945294-5,2031",
            "784-2010-4532229-0,2010",
            "784-1935-0284231-8,1935",
            "784-1944-5708207-4,1944",
            "784-2006-6530100-6,2006",
            "784-2014-7323057-7,2014",
            "784-1956-4970294-1,1956"
    })
    void getBirthYear(String personalCode, int expectedBirthYear) throws PersonalCodeException {
        assertThat(parser.getBirthYear(personalCode)).isEqualTo(expectedBirthYear);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {
            "123",
            "test",
            "784-1980-1234567-9",
            "784-1234-0123456-7",
            "123-1234-0123456-7",
            "123-1234-0123456-77",
            "784198012345679"
    })
    void parseInvalidPersonalCode(String personalCode) {
        assertThatThrownBy(() -> parser.getBirthYear(personalCode))
                .isInstanceOf(PersonalCodeException.class);
    }
}