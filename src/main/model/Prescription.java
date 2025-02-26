package main.model;

import java.time.LocalDate;

public class Prescription {
    private static int nextId = 40000001; // Static variable to keep track of the next ID to be assigned

    private final int PRESCRIPTION_ID;
    private final Doctor doctor;
    private final Patient patient;
    private final Medication medication;
    private final LocalDate date;
    private String instructions;

    // Constructor
    public Prescription(Doctor doctor, Patient patient, Medication medication, String instructions) {
        this.PRESCRIPTION_ID = nextId++;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.instructions = instructions;
        this.date = LocalDate.now();
    }
    public Prescription(Prescription other) {
        this.PRESCRIPTION_ID = nextId++;
        this.doctor = other.doctor;
        this.patient = other.patient;
        this.medication = other.medication;
        this.instructions = other.instructions;
        this.date = LocalDate.now();
    }
    // Getters
    public int getId() {
        return PRESCRIPTION_ID;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Medication getMedication() {
        return medication;
    }

    public String getInstructions() {
        return instructions;
    }

    public LocalDate getPrescriptionDate() {
        return date;
    }

    public LocalDate getPrescriptionExpiry() {
        return date.plusYears(1);
    }


    // Setters
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


    // Methods
    public boolean isExpired() {
        return LocalDate.now().isAfter(getPrescriptionExpiry());
    }

    // toString method
    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + PRESCRIPTION_ID +
                ", doctor= Dr. " + doctor.getFullName() +
                ", patient= " + patient.getFullName() +
                ", medication= " + medication.getName() + " " + medication.getDose() +
                ", instructions= " + instructions +
                ", prescriptionExpiry= " + date +
                '}';
    }
}
