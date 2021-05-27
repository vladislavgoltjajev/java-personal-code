package com.github.vladislavgoltjajev.personalcode.validator;

import com.github.vladislavgoltjajev.personalcode.generator.EstonianPersonalCodeGenerator;
import com.github.vladislavgoltjajev.personalcode.parser.EstonianPersonalCodeParser;

public final class EstonianPersonalCodeValidator {

    private static final String ESTONIAN_ID_CODE_REGEX = "^[1-6]\\d{2}(((0[13578]|1[02])(0[1-9]|[12]\\d|3[01]))"
            + "|((0[469]|11)(0[1-9]|[12]\\d|30))"
            + "|(02(0[1-9]|1\\d|2[0-9])))"
            + "\\d{4}$";

    public boolean isValid(String personalCode) {
        if (!isEstonianPersonalCode(personalCode)) {
            return false;
        }

        try {
            new EstonianPersonalCodeParser().getDateOfBirth(personalCode);
            int controlNumber = Character.getNumericValue(personalCode.charAt(personalCode.length() - 1));
            return controlNumber == new EstonianPersonalCodeGenerator().calculateControlNumber(personalCode);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the personal code follows the correct format.
     */
    public boolean isEstonianPersonalCode(String personalCode) {
        return personalCode != null
                && !personalCode.isBlank()
                && personalCode.matches(ESTONIAN_ID_CODE_REGEX);
    }
}
