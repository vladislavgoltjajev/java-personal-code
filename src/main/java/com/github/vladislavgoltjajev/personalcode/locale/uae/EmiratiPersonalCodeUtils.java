package com.github.vladislavgoltjajev.personalcode.locale.uae;

import com.github.vladislavgoltjajev.personalcode.utility.NumberUtils;

class EmiratiPersonalCodeUtils {

    /**
     * Generates a random year between 1900 and 2099.
     *
     * @return Random year.
     */
    static int getRandomYearOfBirth() {
        return NumberUtils.getRandomNumber(1900, 2099);
    }

    private EmiratiPersonalCodeUtils() {
    }
}
