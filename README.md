# java-personal-code
[![Maven Central](https://img.shields.io/maven-central/v/com.github.vladislavgoltjajev/java-personal-code)](https://mvnrepository.com/artifact/com.github.vladislavgoltjajev/java-personal-code)
[![License](https://img.shields.io/github/license/vladislavgoltjajev/java-personal-code)](https://github.com/vladislavgoltjajev/java-personal-code/blob/main/LICENSE)

Lightweight library for working with government-issued personal identification codes.

## Import
JDK 8 or higher is required for the library to work.
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
* [Estonia](#estonia)
* [Latvia](#latvia)
* [Lithuania](#lithuania)
* [Luxembourg](#luxembourg)
* [Taiwan](#taiwan)

### Estonia
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
            Does not check the personal code's integrity or the validity of the embedded data.
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

### Latvia
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
            Does not check the personal code's integrity or the validity of the embedded data.
        </td>
    </tr>
    <tr>
        <td>isValidUpdatedFormat(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the updated Latvian personal code conforms to the correct format.<br/>
            Does not check the personal code's integrity.
        </td>
    </tr>
    <tr>
        <td>isValidLegacyFormat(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the legacy Latvian personal code conforms to the correct format.<br/>
            Does not check the personal code's integrity or the validity of the embedded data.
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

### Lithuania
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
            Does not check the personal code's integrity or the validity of the embedded data.
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
            Generates an Lithuanian personal code using the given gender and date of birth.<br/>
            The birth order number is generated randomly.
        </td>
    </tr>
    <tr>
        <td>generatePersonalCode(Gender gender, LocalDate dateOfBirth, int birthOrderNumber)</td>
        <td>String</td>
        <td>Generates an Lithuanian personal code using the given gender, date of birth and birth order number.</td>
    </tr>
</table>

### Luxembourg
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
            Does not check the personal code's integrity or the validity of the embedded data.
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

### Taiwan
#### TaiwanesePersonalCodeValidator
<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>isValid(String personalCode)</td>
        <td>boolean</td>
        <td>Checks if the Taiwanese personal code is valid.</td>
    </tr>
    <tr>
        <td>isValidFormat(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the Taiwanese personal code conforms to the correct format.<br/>
            Does not check the personal code's integrity.
        </td>
    </tr>
</table>

#### TaiwanesePersonalCodeParser
<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>getHouseholdRegistrationRegion(String personalCode)</td>
        <td>TaiwaneseRegion</td>
        <td>Returns the person's household registration region (first letter).</td>
    </tr>
    <tr>
        <td>getGender(String personalCode)</td>
        <td>Gender</td>
        <td>Returns the person's gender (first digit).</td>
    </tr>
</table>

#### TaiwanesePersonalCodeGenerator
<table class="table1">
    <tr>
        <th>Method</th>
        <th>Return type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>generateRandomPersonalCode()</td>
        <td>String</td>
        <td>Generates a random Taiwanese personal code.</td>
    </tr>
    <tr>
        <td>generatePersonalCode(TaiwaneseRegion region)</td>
        <td>String</td>
        <td>
            Generates a Taiwanese personal code using the given household registration region.<br/>
            The gender is generated randomly.
        </td>
    </tr>
    <tr>
        <td>generatePersonalCode(Gender gender)</td>
        <td>String</td>
        <td>
            Generates a Taiwanese personal code using the given gender.<br/>
            The household registration region is generated randomly.
        </td>
    </tr>
    <tr>
        <td>generatePersonalCode(TaiwaneseRegion region, Gender gender)</td>
        <td>String</td>
        <td>
            Generates a Taiwanese personal code using the given household registration region and gender.<br/>
            The household registration region is not checked for validity.
        </td>
    </tr>
    <tr>
        <td>generatePersonalCode(TaiwaneseRegion region, Gender gender, boolean checkRegionValidity)</td>
        <td>String</td>
        <td>Generates a Taiwanese personal code using the given household registration region and gender and checks if the given household registration region is valid.</td>
    </tr>
</table>

## Buy me a beer? :beer:
Please donate if you like my work.  
**PayPal**: https://www.paypal.me/VladislavGoltjajev  
**BTC**: 17UKTU4vqJZLp4EMwNTSL2whxqzbWP3Ddk  
**ETH**: 0x3b963c9c518d0278cc68bedf3be461394a227cf2  
**XMR**: 88yGGp8NWChUxG1RvS9hRo7dUkHiSF9E3g1AE2tngCYE7pw6qHzxvBq53SVzUZEdqDT4Xc4niYSFgM1LFz6yfqUm1tKr2BW  
**DOGE**: DB634GFugPxd4GEj8f78KeUpcD1FEfzSvZ
