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
        EstonianPersonalCodeGenerator generator = new EstonianPersonalCodeGenerator();
        String personalCode = generator.generateRandomPersonlCode(); // 37209030023
        EstonianPersonalCodeValidator validator = new EstonianPersonalCodeValidator();
        boolean isFormatValid = validator.isFormatValid(personalCode); // true
        boolean isValid = validator.isValid(personalCode); // true
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
        <td>isFormatValid(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the Estonian personal code conforms to the correct format.<br/>
            Does not check the personal code's integrity and the validity of the embedded data.
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
        <td>Returns the person's gender.</td>
    </tr>
    <tr>
        <td>getDateOfBirth(String personalCode)</td>
        <td>LocalDate</td>
        <td>Returns the person's date of birth.</td>
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
        <td>isLegacyFormatValid(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the legacy Latvian personal code conforms to the correct format.<br/>
            Does not check the personal code's integrity and the validity of the embedded data.
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
        <td>Returns the person's date of birth.</td>
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
        <td>isFormatValid(String personalCode)</td>
        <td>boolean</td>
        <td>
            Checks if the Lithuanian personal code conforms to the correct format.<br/>
            Does not check the personal code's integrity and the validity of the embedded data.
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
        <td>Returns the person's gender.</td>
    </tr>
    <tr>
        <td>getDateOfBirth(String personalCode)</td>
        <td>LocalDate</td>
        <td>Returns the person's date of birth.</td>
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
            Generates an Estonian personal code using the given gender.<br/>
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