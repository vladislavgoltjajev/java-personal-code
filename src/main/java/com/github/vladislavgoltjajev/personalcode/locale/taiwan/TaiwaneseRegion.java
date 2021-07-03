package com.github.vladislavgoltjajev.personalcode.locale.taiwan;

import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;

import java.util.Arrays;
import java.util.Random;

public enum TaiwaneseRegion {

    TAIPEI_CITY("A", 10, true),
    TAICHUNG_CITY("B", 11, true),
    KEELUNG_CITY("C", 12, true),
    TAINAN_CITY("D", 13, true),
    KAOHSIUNG_CITY("E", 14, true),
    NEW_TAIPEI_CITY("F", 15, true),
    YILAN_COUNTY("G", 16, true),
    TAOYUAN_CITY("H", 17, true),
    CHIAYI_CITY("I", 34, true),
    HSINCHU_COUNTY("J", 18, true),
    MIAOLI_COUNTY("K", 19, true),
    NANTOU_COUNTY("M", 21, true),
    CHANGHUA_COUNTY("N", 22, true),
    HSINCHU_CITY("O", 35, true),
    YUNLIN_COUNTY("P", 23, true),
    CHIAYI_COUNTY("Q", 24, true),
    PINGTUNG_COUNTY("T", 27, true),
    HUALIEN_COUNTY("U", 28, true),
    TAITUNG_COUNTY("V", 29, true),
    KINMEN_COUNTY("W", 32, true),
    PENGHU_COUNTY("X", 30, true),
    LIENCHIANG_COUNTY("Z", 33, true),
    TAICHUNG_COUNTY("L", 20, false),
    TAINAN_COUNTY("R", 25, false),
    KAOHSIUNG_COUNTY("S", 26, false),
    YANGMINSHAN_MANAGEMENT_BUREAU("Y", 31, false);

    private final String code;
    private final int value;
    private final boolean issued;

    TaiwaneseRegion(String code, int value, boolean issued) {
        this.code = code;
        this.value = value;
        this.issued = issued;
    }

    public static TaiwaneseRegion findByCode(String code) throws PersonalCodeException {
        return Arrays.stream(TaiwaneseRegion.values())
                .filter(r -> r.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new PersonalCodeException("Invalid Taiwanese region code"));
    }

    public static TaiwaneseRegion getRandomRegion() {
        return TaiwaneseRegion.values()[new Random().nextInt(TaiwaneseRegion.values().length)];
    }

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    public boolean isIssued() {
        return issued;
    }
}
