package com.github.vladislavgoltjajev.personalcode.locale.luxembourg;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LuxembourgishPersonalCodeParserTest {

    LuxembourgishPersonalCodeParser parser;

    @BeforeEach
    void setUp() {
        parser = new LuxembourgishPersonalCodeParser();
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {
            "123",
            "test",
            "2087022955450",
            "2001992993451",
            "2015-060607344",
            "20150606073441"
    })
    void parseInvalidPersonalCode(String personalCode) {
        assertThatThrownBy(() -> parser.getDateOfBirth(personalCode))
                .isInstanceOf(PersonalCodeException.class);
    }

    @ParameterizedTest
    @CsvSource({
            "2049081577690,2049-08-15",
            "2026060593451,2026-06-05",
            "2000022900197,2000-02-29",
            "1944051267737,1944-05-12",
            "2087020155450,2087-02-01",
            "1973100283493,1973-10-02",
            "1917031977873,1917-03-19",
            "2015060607344,2015-06-06"
    })
    void getDateOfBirth(String personalCode, LocalDate expectedDateOfBirth) throws PersonalCodeException {
        assertThat(parser.getDateOfBirth(personalCode)).isEqualTo(expectedDateOfBirth);
    }

    @ParameterizedTest
    @CsvSource({
            "2000022900197,21",
            "1944051267737,77",
            "1973100283493,47",
            "1917031977873,104",
            "2015060607344,6"
    })
    void getAge(String personalCode, int expectedAge) throws PersonalCodeException {
        assertThat(parser.getAge(personalCode).getYears()).isGreaterThanOrEqualTo(expectedAge);
    }

    @ParameterizedTest
    @CsvSource({
            "2049081577690,776",
            "2026060593451,934",
            "2000022900197,1",
            "1944051267737,677",
            "2087020155450,554",
            "1973100283493,834",
            "1917031977873,778",
            "2015060607344,73"
    })
    void getBirthOrderNumber(String personalCode, int expectedBirthOrderNumber) throws PersonalCodeException {
        assertThat(parser.getBirthOrderNumber(personalCode)).isEqualTo(expectedBirthOrderNumber);
    }

}