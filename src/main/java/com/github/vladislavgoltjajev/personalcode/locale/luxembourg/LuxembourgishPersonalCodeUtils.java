package com.github.vladislavgoltjajev.personalcode.locale.luxembourg;

import com.github.vladislavgoltjajev.personalcode.utility.ChecksumUtils;
import com.github.vladislavgoltjajev.personalcode.utility.DateUtils;

import java.time.LocalDate;
import java.util.Random;

class LuxembourgishPersonalCodeUtils {

    /**
     * Calculates the Luxembourgish personal code checksum.
     * The checksum is a combination of the Luhn and Verhoeff algorithm checksums.
     *
     * @param personalCode Luxembourgish personal code without the checksum.
     * @return Checksum.
     */
    static String getChecksum(String personalCode) {
        return "" + ChecksumUtils.getLuhnChecksum(personalCode) + ChecksumUtils.getVerhoeffChecksum(personalCode);
    }

    /**
     * Generates a random date between the earliest (01.01.1800) and latest (31.12.2099) possible dates of birth.
     *
     * @return Random date of birth.
     */
    static LocalDate getRandomDateOfBirth() {
        return DateUtils.getRandomDate(LuxembourgishPersonalCodeConstants.MINIMUM_DATE, LuxembourgishPersonalCodeConstants.MAXIMUM_DATE);
    }

    /**
     * Generates a random birth order number (0-999).
     *
     * @return Random birth order number.
     */
    static int getRandomBirthOrderNumber() {
        return new Random().nextInt(1000);
    }


    private LuxembourgishPersonalCodeUtils() {
    }
}
