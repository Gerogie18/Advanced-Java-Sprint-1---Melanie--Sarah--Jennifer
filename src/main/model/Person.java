package main.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class Person {
    private static int nextId = 10000001; // Static variable to keep track of the next ID to be assigned

    private final int id;
    private String lastName;
    private String firstName;
    private final LocalDate birthdate;
    private String phoneNumber;

    // Constructor
    public Person(String lastName, String firstName, String birthdateStr, String phoneNumber) {
        this.id = nextId;
        nextId++;
        this.lastName = lastName;
        this.firstName = firstName;
        try {
            this.birthdate = LocalDate.parse(birthdateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid birthdate format, expected YYYY-MM-DD.");
        }
        setPhoneNumber(phoneNumber);
    }
    public Person(String lastName, String firstName, String birthdateStr, String phoneNumber, int offset) {
        this.id = nextId + offset;
        nextId++;
        this.lastName = lastName;
        this.firstName = firstName;
        try {
            this.birthdate = LocalDate.parse(birthdateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid birthdate format, expected YYYY-MM-DD.");
        }
        setPhoneNumber(phoneNumber);
    }
    public Person(Person otherPerson) {
        this.id = nextId;
        nextId++;
        this.lastName = otherPerson.lastName;
        this.firstName = otherPerson.firstName;
        this.birthdate = otherPerson.birthdate;
        this.phoneNumber = otherPerson.phoneNumber;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number. Must be 10 digits long.");
        }
        this.phoneNumber = formatPhoneNumber(phoneNumber);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String digits = phoneNumber.replaceAll("\\D+", "");
        return digits.length() == 10;
    }

    private String formatPhoneNumber(String phoneNumber) {
        String digits = phoneNumber.replaceAll("\\D+", "");
        return String.format("(%s) %s-%s",
                digits.substring(0, 3),
                digits.substring(3, 6),
                digits.substring(6, 10));
    }


    public int calculateAge(LocalDate birthdate) {
        // Calculate the period between the birthdate and today
        Period elapsedTime = Period.between(birthdate, LocalDate.now());
        return elapsedTime.getYears();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("%s %s (%d years old), %s",
                firstName,
                lastName,
                calculateAge(birthdate),
                phoneNumber);
    }
}
