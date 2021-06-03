package com.github.vladislavgoltjajev.personalcode.locale.lithuania;

import com.github.vladislavgoltjajev.personalcode.enums.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LithuanianPersonalCodeParserTest {

    @ParameterizedTest
    @CsvSource({
            "17605130008,145",
            "29912120004,121",
            "34503020000,76",
            "47508030046,45",
            "50109130003,17",
            "60302050016,15",
            "60002290003,21",
            "39912310174,21",
            "50002290046,21"
    })
    void getAge(String personalCode, int expectedAge) throws PersonalCodeException {
        LithuanianPersonalCodeParser parser = new LithuanianPersonalCodeParser();
        assertThat(parser.getAge(personalCode).getYears()).isGreaterThanOrEqualTo(expectedAge);
    }

    @ParameterizedTest
    @CsvSource({
            "17605130008,MALE",
            "29912120004,FEMALE",
            "34503020000,MALE",
            "47508030046,FEMALE",
            "50109130003,MALE",
            "60302050016,FEMALE",
            "60002290003,FEMALE",
            "39912310174,MALE",
            "50002290046,MALE"
    })
    void getGender(String personalCode, Gender expectedGender) throws PersonalCodeException {
        LithuanianPersonalCodeParser parser = new LithuanianPersonalCodeParser();
        assertThat(parser.getGender(personalCode)).isEqualTo(expectedGender);
    }

    @ParameterizedTest
    @CsvSource({
            "17605130008,1876-05-13",
            "29912120004,1899-12-12",
            "34503020000,1945-03-02",
            "47508030046,1975-08-03",
            "50109130003,2001-09-13",
            "60302050016,2003-02-05",
            "60002290003,2000-02-29",
            "39912310174,1999-12-31",
            "50002290046,2000-02-29"
    })
    void getDateOfBirth(String personalCode, LocalDate expectedDateOfBirth) throws PersonalCodeException {
        LithuanianPersonalCodeParser parser = new LithuanianPersonalCodeParser();
        assertThat(parser.getDateOfBirth(personalCode)).isEqualTo(expectedDateOfBirth);
    }

    @ParameterizedTest
    @CsvSource({
            "17605130008,0",
            "29912120004,0",
            "34503020000,0",
            "47508030046,4",
            "50109130003,0",
            "60302050016,1",
            "60002290003,0",
            "39912310174,17",
            "50002290046,4"
    })
    void getBirthOrderNumber(String personalCode, int expectedBirthOrderNumber) throws PersonalCodeException {
        LithuanianPersonalCodeParser parser = new LithuanianPersonalCodeParser();
        assertThat(parser.getBirthOrderNumber(personalCode)).isEqualTo(expectedBirthOrderNumber);
    }
}