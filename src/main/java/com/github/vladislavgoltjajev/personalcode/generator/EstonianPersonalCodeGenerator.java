package com.github.vladislavgoltjajev.personalcode.generator;

import com.github.vladislavgoltjajev.personalcode.enums.Gender;
import com.github.vladislavgoltjajev.personalcode.exception.PersonalCodeException;
import com.github.vladislavgoltjajev.personalcode.utility.DateUtils;
import com.github.vladislavgoltjajev.personalcode.utility.ThreadUtils;
import com.github.vladislavgoltjajev.personalcode.validator.EstonianPersonalCodeValidator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public final class EstonianPersonalCodeGenerator {

    String generateRandomPersonalCode() throws PersonalCodeException {
        return generatePersonalCode(getRandomGender(), generateRandomDateOfBirth(), generateRandomBirthOrderNumber());
    }

    public String generatePersonalCode(Gender gender, LocalDate dateOfBirth) throws PersonalCodeException {
        return generatePersonalCode(gender, dateOfBirth, generateRandomBirthOrderNumber());
    }

    public String generatePersonalCode(Gender gender, LocalDate dateOfBirth, int birthOrderNumber)
            throws PersonalCodeException {
        if (dateOfBirth == null || dateOfBirth.isBefore(DateUtils.MINIMUM_DATE) || dateOfBirth.isAfter(DateUtils.MAXIMUM_DATE)) {
            throw new PersonalCodeException(String.format("Birth year must be between %d and %d", DateUtils.MINIMUM_DATE.getYear(),
                    DateUtils.MAXIMUM_DATE.getYear()));
        }

        if (birthOrderNumber < 0 || birthOrderNumber > 999) {
            throw new PersonalCodeException("Birth order number must be between 0 and 999");
        }

        String personalCodeWithoutControlNumber = calculateGenderIdentifier(gender, dateOfBirth)
                + dateOfBirth.format(DateUtils.DATE_FORMATTER).substring(2)
                + String.format("%03d", birthOrderNumber); // Add padding zeroes.
        return personalCodeWithoutControlNumber + calculateControlNumber(personalCodeWithoutControlNumber);
    }

    /**
     * The control number (last digit) is calculated by multiplying the first 10 digits of the personal code by
     * the corresponding number in an array of multipliers [1, 2, 3, 4, 5, 6, 7, 8, 9, 1],
     * summing up each result and calculating the remainder of the sum divided by 11.
     * If the resulting control number is 10, the process is repeated with a different array of multipliers
     * [3, 4, 5, 6, 7, 8, 9, 1, 2, 3].
     * If the resulting control number is 10 again, the person's control number is set to 0.
     */
    public int calculateControlNumber(String personalCode) throws PersonalCodeException {
        validatePersonalCode(personalCode);
        String[] numberArray = personalCode.substring(0, 10).split("");
        List<Integer> numberList = Arrays.stream(numberArray)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        int sum = 0;
        int[] multipliers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};

        for (int i = 0; i < numberList.size(); i++) {
            sum += numberList.get(i) * multipliers[i];
        }

        int parsedControlNumber = sum % 11;

        if (parsedControlNumber == 10) {
            sum = 0;
            multipliers = new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2, 3};

            for (int i = 0; i < numberList.size(); i++) {
                sum += numberList.get(i) * multipliers[i];
            }

            parsedControlNumber = sum % 11;

            if (parsedControlNumber == 10) {
                return 0;
            }
        }

        return parsedControlNumber;
    }

    /**
     * Calculates the gender identifier for the given person's gender and birth year.
     * <p>
     * Male:
     * years 1800-1899 - 1
     * years 1900-1999 - 3
     * years 2000-2099 - 5
     * <p>
     * Female:
     * years 1800-1899 - 2
     * years 1900-1999 - 4
     * years 2000-2099 - 6
     */
    private int calculateGenderIdentifier(Gender gender, LocalDate dateOfBirth) {
        int birthYear = dateOfBirth.getYear();
        boolean isMale = Gender.MALE.equals(gender);

        if (birthYear >= 1800 && birthYear <= 1899) {
            return isMale ? 1 : 2;
        } else if (birthYear >= 1900 && birthYear <= 1999) {
            return isMale ? 3 : 4;
        }

        return isMale ? 5 : 6;
    }

    private Gender getRandomGender() {
        return new Random().nextInt(2) == 0 ? Gender.MALE : Gender.FEMALE;
    }

    /**
     * Generates a random date between the earliest and latest possible birth dates (inclusive).
     */
    private LocalDate generateRandomDateOfBirth() {
        int daysBetween = (int) ChronoUnit.DAYS.between(DateUtils.MINIMUM_DATE, DateUtils.MAXIMUM_DATE);
        return DateUtils.MINIMUM_DATE.plusDays(new Random().nextInt(daysBetween + 1));
    }

    private int generateRandomBirthOrderNumber() {
        return new Random().nextInt(1000);
    }

    private void validatePersonalCode(String personalCode) throws PersonalCodeException {
        if (!ThreadUtils.stackContainsClass(EstonianPersonalCodeValidator.class)) {
            // Needed to check the overall format when generating a control number for an incomplete personal code.
            if (personalCode != null
                    && !personalCode.isBlank()
                    && personalCode.matches("\\d{10}")) {
                personalCode += "0";
            }

            if (!new EstonianPersonalCodeValidator().isEstonianPersonalCode(personalCode)) {
                throw new PersonalCodeException("Invalid Estonian personal code format");
            }
        }
    }
}
