# java-personal-code
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://github.com/vladislavgoltjajev/java-personal-code/blob/main/LICENSE)

Lightweight library for working with government-issued personal identification codes.

## Import
The library is available on Maven Central.  
JDK 8 or higher is required for the library to work.
### Gradle
```groovy
dependencies {
    implementation 'com.github.vladislavgoltjajev:java-personal-code:1.0'
}
```
### Maven
```xml
<dependency>
    <groupId>com.github.vladislavgoltjajev</groupId>
    <artifactId>java-personal-code</artifactId>
    <version>1.0</version>
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
        <td>Checks if the Latvian personal code (both legacy and updated) is valid.</td>
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