import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String lastName;
    private String firstName;
    private final LocalDate birthdate;
    private String phoneNumber;

    // Constructor
    public Person(String lastName, String firstName, String birthdateStr, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = LocalDate.parse(birthdateStr);
        this.phoneNumber = phoneNumber;
    }
    public Person(Person otherPerson) {
        this.lastName = otherPerson.lastName;
        this.firstName = otherPerson.firstName;
        this.birthdate = otherPerson.birthdate;
        this.phoneNumber = otherPerson.phoneNumber;
    }

    // Getters and Setters
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
        this.phoneNumber = phoneNumber;
    }

    public int returnAge() {
        LocalDate today = LocalDate.now();

        // Calculate the period between the birthdate and today
        Period elapsedTime = Period.between(birthdate, today);
        return elapsedTime.getYears();
    }


    // toString method
    @Override
    public String toString() {
        return firstName + " " + lastName + "(" + returnAge() + "), " + phoneNumber;
    }
}
