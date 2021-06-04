# java-personal-code
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://github.com/vladislavgoltjajev/java-personal-code/blob/main/LICENSE)

Lightweight library for working with government-issued personal identification codes.

## Import
:exclamation::exclamation::exclamation: Library is currently not hosted anywhere. :exclamation::exclamation::exclamation:   
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