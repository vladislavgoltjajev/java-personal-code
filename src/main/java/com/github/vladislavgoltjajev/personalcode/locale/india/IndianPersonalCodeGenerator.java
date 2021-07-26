package com.github.vladislavgoltjajev.personalcode.locale.india;

import com.github.vladislavgoltjajev.personalcode.utility.ChecksumUtils;
import com.github.vladislavgoltjajev.personalcode.utility.NumberUtils;

public class IndianPersonalCodeGenerator {

    /**
     * Generates an Indian personal code.
     *
     * @return Indian personal code.
     */
    public String generatePersonalCode() {
        String personalCodeWithoutChecksum = NumberUtils.getRandomNumberWithLeadingZeroes(4) + "-"
                + NumberUtils.getRandomNumberWithLeadingZeroes(4) + "-"
                + NumberUtils.getRandomNumberWithLeadingZeroes(3);
        return personalCodeWithoutChecksum + ChecksumUtils.getVerhoeffChecksum(personalCodeWithoutChecksum.replaceAll("-", ""));
    }
}
