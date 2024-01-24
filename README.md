# Java Personal Code

[![Maven Central](https://img.shields.io/maven-central/v/com.github.vladislavgoltjajev/java-personal-code)](https://mvnrepository.com/artifact/com.github.vladislavgoltjajev/java-personal-code)
[![License](https://img.shields.io/github/license/vladislavgoltjajev/java-personal-code)](https://github.com/vladislavgoltjajev/java-personal-code/blob/main/LICENSE)

Lightweight library for working with government-issued personal identification codes.

## Import

Java 8 or higher is required for the library to work.

### Gradle

```groovy
dependencies {
    implementation 'com.github.vladislavgoltjajev:java-personal-code:X.X'
}
```

### Maven

```xml

<dependency>
    <groupId>com.github.vladislavgoltjajev</groupId>
    <artifactId>java-personal-code</artifactId>
    <version>X.X</version>
</dependency>
```

## Usage

```java
public class Test {

    /**
     * Example operations using an Estonian personal code.
     */
    public static void main(String[] args) {
        // Generate personal code.
        EstonianPersonalCodeGenerator generator = new EstonianPersonalCodeGenerator();
        String personalCode = generator.generateRandomPersonlCode(); // 37209030023
        // Validate personal code.
        EstonianPersonalCodeValidator validator = new EstonianPersonalCodeValidator();
        boolean isValidFormat = validator.isValidFormat(personalCode); // true
        boolean isValid = validator.isValid(personalCode); // true
        // Extract data from personal code.
        EstonianPersonalCodeParser parser = new EstonianPersonalCodeParser();
        LocalDate dateOfBirth = parser.getDateOfBirth(personalCode); // 03.09.1972
        Gender gender = parser.getGender(personalCode); // MALE
        int birthOrderNumber = parser.getBirthOrderNumber(personalCode); // 2
    }
}
```

## API

* ![EE](https://github.com/madebybowtie/FlagKit/raw/master/Assets/PNG/EE.png) [Estonia](#estonia)
* ![IN](https://github.com/madebybowtie/FlagKit/raw/master/Assets/PNG/IN.png) [India](#india)
* ![LV](https://github.com/madebybowtie/FlagKit/raw/master/Assets/PNG/LV.png) [Latvia](#latvia)
* ![LT](https://github.com/madebybowtie/FlagKit/raw/master/Assets/PNG/LT.png) [Lithuania](#lithuania)
* ![LU](https://github.com/madebybowtie/FlagKit/raw/master/Assets/PNG/LU.png) [Luxembourg](#luxembourg)
* ![UE](https://github.com/madebybowtie/FlagKit/raw/master/Assets/PNG/AE.png) [United Arab Emirates](#uae)

### <a name="estonia"></a> ![EE](https://github.com/madebybowtie/FlagKit/raw/master/Assets/PNG/EE.png) Estonia

`GYYMMDDXXXC`

* `G` - gender and birth century indicator (1, 3, 5 - male; 2, 4, 6 - female)
* `YYMMDD` - date of birth
* `XXX` - birth order number
* `C` - checksum

Example: 47508030046.

#### EstonianPersonalCodeValidator

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>isValid(String personalCode)</td>
        <td>boolean</td>
        <td>Checks if the Estonian personal code is valid.</td>
    </tr>
    <tr>
        <td>isValidFormat(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the Estonian personal code conforms to the correct format.<br/>
            Does not check the integrity of the personal code or the validity of the embedded data.
        </td>
    </tr>
</table>

#### EstonianPersonalCodeParser

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>getGender(String personalCode)</td>
        <td>Gender</td>
        <td>Returns the person's gender (first digit).</td>
    </tr>
    <tr>
        <td>getDateOfBirth(String personalCode)</td>
        <td>LocalDate</td>
        <td>Returns the person's date of birth (digits 2-7).</td>
    </tr>
    <tr>
        <td>getAge(String personalCode)</td>
        <td>Period</td>
        <td>Returns the person's age.</td>
    </tr>
    <tr>
        <td>getBirthOrderNumber(String personalCode)</td>
        <td>int</td>
        <td>Returns the person's birth order number (digits 8-10).</td>
    </tr>
</table>

#### EstonianPersonalCodeGenerator

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>generateRandomPersonalCode()</td>
        <td>String</td>
        <td>Generates a random Estonian personal code.</td>
    </tr>
    <tr>
        <td>generatePersonalCode(Gender gender)</td>
        <td>String</td>
        <td>
            Generates an Estonian personal code using the given gender.<br/>
            The date of birth and birth order number are generated randomly.
        </td>
    </tr>
    <tr>
        <td>generatePersonalCode(Gender gender, LocalDate dateOfBirth)</td>
        <td>String</td>
        <td>
            Generates an Estonian personal code using the given gender and date of birth.<br/>
            The birth order number is generated randomly.
        </td>
    </tr>
    <tr>
        <td>generatePersonalCode(Gender gender, LocalDate dateOfBirth, int birthOrderNumber)</td>
        <td>String</td>
        <td>Generates an Estonian personal code using the given gender, date of birth and birth order number.</td>
    </tr>
</table>

### <a name="india"></a> ![IN](https://github.com/madebybowtie/FlagKit/raw/master/Assets/PNG/IN.png) India

`XXXX-XXXX-XXXC`

* `XXXX-XXXX-XXX` - random numbers
* `C` - checksum (Verhoeff algorithm)

Example: 9185-8655-0944.

#### IndianPersonalCodeValidator

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>isValid(String personalCode)</td>
        <td>boolean</td>
        <td>Checks if the Indian personal code is valid.</td>
    </tr>
    <tr>
        <td>isValidFormat(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the Indian personal code conforms to the correct format.<br/>
            Does not check the integrity of the personal code.
        </td>
    </tr>
</table>

#### IndianPersonalCodeGenerator

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>generatePersonalCode()</td>
        <td>String</td>
        <td>Generates an Indian personal code.</td>
    </tr>
</table>

### <a name="latvia"></a> ![LV](https://github.com/madebybowtie/FlagKit/raw/master/Assets/PNG/LV.png) Latvia

`32XXXX-XXXXC` (updated format since 01.07.2017)

* `32` - updated format indicator
* `XXXX-XXXX` - random numbers
* `C` - checksum

Example: 323691-93794.

`YYMMDD-BXXXC` (legacy format)

* `YYMMDD` - date of birth
* `B` - birth century indicator (0 - 19th, 1 - 20th, 2 - 21st)
* `XXX` - birth order number
* `C` - checksum

Example: 290156-11605.

#### LatvianPersonalCodeValidator

<table>
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>isValid(String personalCode)</td>
        <td>boolean</td>
        <td>Checks if the Latvian personal code (either updated or legacy) is valid.</td>
    </tr>
    <tr>
        <td>isValidUpdatedPersonalCode(String personalCode)</td>
        <td>boolean</td>
        <td>Checks if the updated Latvian personal code is valid.</td>
    </tr>
    <tr>
        <td>isValidLegacyPersonalCode(String personalCode)</td>
        <td>boolean</td>
        <td>Checks if the legacy Latvian personal code is valid.</td>
    </tr>
    <tr>
        <td>isValidFormat(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the specified Latvian personal code conforms to the correct legacy or updated format.<br/>
            Does not check the integrity of the personal code or the validity of the embedded data.
        </td>
    </tr>
    <tr>
        <td>isValidUpdatedFormat(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the updated Latvian personal code conforms to the correct format.<br/>
            Does not check the integrity of the personal code.
        </td>
    </tr>
    <tr>
        <td>isValidLegacyFormat(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the legacy Latvian personal code conforms to the correct format.<br/>
            Does not check the integrity of the personal code or the validity of the embedded data.
        </td>
    </tr>
</table>

#### LatvianPersonalCodeParser

:exclamation: Only works with legacy Latvian personal codes.
<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>getDateOfBirth(String personalCode)</td>
        <td>LocalDate</td>
        <td>Returns the person's date of birth (digits 1-6).</td>
    </tr>
    <tr>
        <td>getAge(String personalCode)</td>
        <td>Period</td>
        <td>Returns the person's age.</td>
    </tr>
    <tr>
        <td>getBirthOrderNumber(String personalCode)</td>
        <td>int</td>
        <td>Returns the person's birth order number (digits 8-10).</td>
    </tr>
</table>

#### LatvianPersonalCodeGenerator

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>generateRandomPersonalCode()</td>
        <td>String</td>
        <td>Generates a random Latvian personal code.</td>
    </tr>
    <tr>
        <td>generateRandomUpdatedPersonalCode()</td>
        <td>String</td>
        <td>
            Generates a random updated Latvian personal code.<br/>
            May contain a dash between the 6th and 7th digit.
        </td>
    </tr>
    <tr>
        <td>generateRandomUpdatedPersonalCode(boolean addDash)</td>
        <td>String</td>
        <td>Generates a random updated Latvian personal code.</td>
    </tr>
    <tr>
        <td>generateRandomLegacyPersonalCode()</td>
        <td>String</td>
        <td>Generates a random legacy Latvian personal code.</td>
    </tr>
    <tr>
        <td>generateLegacyPersonalCode(LocalDate dateOfBirth)</td>
        <td>String</td>
        <td>
            Generates a legacy Latvian personal code using the given date of birth.<br/>
            The birth order number is generated randomly.
        </td>
    </tr>
    <tr>
        <td>generateLegacyPersonalCode(LocalDate dateOfBirth, int birthOrderNumber)</td>
        <td>String</td>
        <td>Generates a legacy Latvian personal code using the given date of birth and birth order number.</td>
    </tr>
</table>

### <a name="lithuania"></a> ![LT](https://github.com/madebybowtie/FlagKit/raw/master/Assets/PNG/LT.png) Lithuania

`GYYMMDDXXXC`

* `G` - gender and birth century indicator (1, 3, 5 - male; 2, 4, 6 - female)
* `YYMMDD` - date of birth
* `XXX` - birth order number
* `C` - checksum

Example: 50109130003.

#### LithuanianPersonalCodeValidator

<table>
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>isValid(String personalCode)</td>
        <td>boolean</td>
        <td>Checks if the Lithuanian personal code is valid.</td>
    </tr>
    <tr>
        <td>isValidFormat(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the Lithuanian personal code conforms to the correct format.<br/>
            Does not check the integrity of the personal code or the validity of the embedded data.
        </td>
    </tr>
</table>

#### LithuanianPersonalCodeParser

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>getGender(String personalCode)</td>
        <td>Gender</td>
        <td>Returns the person's gender (first digit).</td>
    </tr>
    <tr>
        <td>getDateOfBirth(String personalCode)</td>
        <td>LocalDate</td>
        <td>Returns the person's date of birth (digits 2-7).</td>
    </tr>
    <tr>
        <td>getAge(String personalCode)</td>
        <td>Period</td>
        <td>Returns the person's age.</td>
    </tr>
    <tr>
        <td>getBirthOrderNumber(String personalCode)</td>
        <td>int</td>
        <td>Returns the person's birth order number (digits 8-10).</td>
    </tr>
</table>

#### LithuanianPersonalCodeGenerator

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>generateRandomPersonalCode()</td>
        <td>String</td>
        <td>Generates a random Lithuanian personal code.</td>
    </tr>
    <tr>
        <td>generatePersonalCode(Gender gender)</td>
        <td>String</td>
        <td>
            Generates a Lithuanian personal code using the given gender.<br/>
            The date of birth and birth order number are generated randomly.
        </td>
    </tr>
    <tr>
        <td>generatePersonalCode(Gender gender, LocalDate dateOfBirth)</td>
        <td>String</td>
        <td>
            Generates a Lithuanian personal code using the given gender and date of birth.<br/>
            The birth order number is generated randomly.
        </td>
    </tr>
    <tr>
        <td>generatePersonalCode(Gender gender, LocalDate dateOfBirth, int birthOrderNumber)</td>
        <td>String</td>
        <td>Generates a Lithuanian personal code using the given gender, date of birth and birth order number.</td>
    </tr>
</table>

### <a name="luxembourg"></a> ![LU](https://github.com/madebybowtie/FlagKit/raw/master/Assets/PNG/LU.png) Luxembourg

`YYYYMMDDXXXCC`

* `YYYYMMDD` - date of birth
* `XXX` - birth order number
* `CC` - checksum (Luhn and Verhoeff algorithms)

Example: 1944051267737.

#### LuxembourgishPersonalCodeValidator

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>isValid(String personalCode)</td>
        <td>boolean</td>
        <td>Checks if the Luxembourgish personal code is valid.</td>
    </tr>
    <tr>
        <td>isValidFormat(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the Luxembourgish personal code conforms to the correct format.<br/>
            Does not check the integrity of the personal code or the validity of the embedded data.
        </td>
    </tr>
</table>

#### LuxembourgishPersonalCodeParser

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>getDateOfBirth(String personalCode)</td>
        <td>LocalDate</td>
        <td>Returns the person's date of birth (digits 1-8).</td>
    </tr>
    <tr>
        <td>getAge(String personalCode)</td>
        <td>Period</td>
        <td>Returns the person's age.</td>
    </tr>
    <tr>
        <td>getBirthOrderNumber(String personalCode)</td>
        <td>int</td>
        <td>Returns the person's birth order number (digits 9-11).</td>
    </tr>
</table>

#### LuxembourgishPersonalCodeGenerator

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>generateRandomPersonalCode()</td>
        <td>String</td>
        <td>Generates a random Luxembourgish personal code.</td>
    </tr>
    <tr>
        <td>generatePersonalCode(LocalDate dateOfBirth)</td>
        <td>String</td>
        <td>
            Generates a Luxembourgish personal code using the given date of birth.<br/>
            The birth order number is generated randomly.
        </td>
    </tr>
    <tr>
        <td>generatePersonalCode(LocalDate dateOfBirth, int birthOrderNumber)</td>
        <td>String</td>
        <td>Generates a Luxembourgish personal code using the given date of birth and birth order number.</td>
    </tr>
</table>

### <a name="uae"></a> ![AE](https://github.com/madebybowtie/FlagKit/raw/master/Assets/PNG/AE.png) United Arab Emirates

`784-YYYY-XXXXXXX-C`

* `784` - ISO 3166-1 numeric code for the UAE
* `YYYY` - year of birth
* `XXXXXXX` - random number
* `C` - checksum (Luhn algorithm)

Example: 784-1935-0284231-8.

#### EmiratiPersonalCodeValidator

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>isValid(String personalCode)</td>
        <td>boolean</td>
        <td>Checks if the Emirati personal code is valid.</td>
    </tr>
    <tr>
        <td>isValidFormat(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the Emirati personal code conforms to the correct format.<br/>
            Does not check the integrity of the personal code.
        </td>
    </tr>
</table>

#### EmiratiPersonalCodeParser

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>getBirthYear(String personalCode)</td>
        <td>int</td>
        <td>Returns the person's birth year (digits 4-7).</td>
    </tr>
</table>

#### EmiratiPersonalCodeGenerator

<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>generateRandomPersonalCode()</td>
        <td>String</td>
        <td>Generates a random Emirati personal code.</td>
    </tr>
    <tr>
        <td>generatePersonalCode(LocalDate dateOfBirth)</td>
        <td>String</td>
        <td>Generates an Emirati personal code using the given date of birth.</td>
    </tr>
    <tr>
        <td>generatePersonalCode(int birthYear)</td>
        <td>String</td>
        <td>Generates an Emirati personal code using the given birth year.</td>
    </tr>
</table>

## Attributions

Cryptocurrency icons: https://github.com/ErikThiart/cryptocurrency-icons  
Country flags: https://github.com/madebybowtie/FlagKit