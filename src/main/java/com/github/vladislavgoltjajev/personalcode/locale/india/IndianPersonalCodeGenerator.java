package com.github.vladislavgoltjajev.personalcode.locale.india;

import com.github.vladislavgoltjajev.personalcode.utility.ChecksumUtils;
import com.github.vladislavgoltjajev.personalcode.utility.NumberUtils;

public final class IndianPersonalCodeGenerator {

    /**
     * Generates an Indian p[ersonal code.
     *
     * @return Indian personal code.
     */
    public String generatePersonalCode() {
        String aadhaarWithoutChecksum = NumberUtils.getRandomNumberWithLeadingZeroes(4) + "-"
                + NumberUtils.getRandomNumberWithLeadingZeroes(4) + "-"
                + NumberUtils.getRandomNumberWithLeadingZeroes(3);
        return aadhaarWithoutChecksum + ChecksumUtils.getVerhoeffChecksum(aadhaarWithoutChecksum.replaceAll("-", ""));
    }
}
