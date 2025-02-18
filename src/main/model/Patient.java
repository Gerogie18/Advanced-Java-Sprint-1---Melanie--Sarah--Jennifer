package main.model;

public class Patient extends Person {
    private String medications;
    private String prescriptions;

    // Constructor
    public Patient(String lastName, String firstName, String birthdateStr, String phoneNumber, String medications, String prescriptions) {
        super(lastName, firstName, birthdateStr, phoneNumber);  // Call to superclass (main.model.Person) constructor
        this.medications = medications;
        this.prescriptions = prescriptions;
    }

    //copyConstructor
    public Patient(Patient otherPatient) {
        super(otherPatient);  // Call to superclass (main.model.Person) constructor
        this.medications = otherPatient.medications;
        this.prescriptions = otherPatient.prescriptions;
    }

    // Getters and Setters
    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    // toString method
    @Override
    public String toString() {
        return super.toString() + ", Medications: " + medications + ", Prescriptions: " + prescriptions;
    }
}
