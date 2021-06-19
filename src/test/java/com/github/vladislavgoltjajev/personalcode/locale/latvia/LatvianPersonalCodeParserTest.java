package com.github.vladislavgoltjajev.personalcode.locale.latvia;

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

class LatvianPersonalCodeParserTest {

    private LatvianPersonalCodeParser parser;

    @BeforeEach
    void setUp() {
        parser = new LatvianPersonalCodeParser();
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {
            "123",
            "test",
            "37605030291",
            "77605030291",
            "60319113016",
            "99999999999",
            "999999-99999",
            "39912310173",
            "39002310001",
            "50102290005",
            "501022900051",
            "325-442-49548",
            "335442-49548",
            "315442-49548",
            "290213-29381",
            "180565-05611"
    })
    void parseInvalidPersonalCode(String personalCode) {
        assertThatThrownBy(() -> parser.getDateOfBirth(personalCode))
                .isInstanceOf(PersonalCodeException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "325442-49548",
            "329174-26924",
            "32550128526",
            "32220316612",
            "323989-74020",
            "32744759186",
            "322589-41469",
            "329294-80588",
            "32240310721"
    })
    void parseUpdatedFormatPersonalCode(String personalCode) {
        assertThatThrownBy(() -> parser.getDateOfBirth(personalCode))
                .isInstanceOf(PersonalCodeException.class)
                .hasMessage("Cannot extract data from updated Latvian personal codes");
    }

    @ParameterizedTest
    @CsvSource({
            "180565-05610,1865-05-18",
            "061193-06460,1893-11-06",
            "280638-19406,1938-06-28",
            "290212-29381,2012-02-29",
            "211244-14874,1944-12-21",
            "290156-11605,1956-01-29",
            "060287-06450,1887-02-06",
            "140761-19428,1961-07-14",
            "120768-18764,1968-07-12",
    })
    void getDateOfBirth(String personalCode, LocalDate expectedDateOfBirth) throws PersonalCodeException {
        assertThat(parser.getDateOfBirth(personalCode)).isEqualTo(expectedDateOfBirth);
    }

    @ParameterizedTest
    @CsvSource({
            "180565-05610,156",
            "061193-06460,127",
            "280638-19406,82",
            "290212-29381,9",
            "211244-14874,76",
            "290156-11605,65",
            "060287-06450,134",
            "140761-19428,59",
            "120768-18764,52",
    })
    void getAge(String personalCode, int expectedAge) throws PersonalCodeException {
        assertThat(parser.getAge(personalCode).getYears()).isGreaterThanOrEqualTo(expectedAge);
    }

    @ParameterizedTest
    @CsvSource({
            "180565-05610,561",
            "061193-06460,646",
            "280638-19406,940",
            "290212-29381,938",
            "211244-14874,487",
            "290156-11605,160",
            "060287-06450,645",
            "140761-19428,942",
            "120768-18764,876",
    })
    void getBirthOrderNumber(String personalCode, int expectedBirthOrderNumber) throws PersonalCodeException {
        assertThat(parser.getBirthOrderNumber(personalCode)).isEqualTo(expectedBirthOrderNumber);
    }
}