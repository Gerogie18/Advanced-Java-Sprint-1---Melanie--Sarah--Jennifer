package main.model;

import java.time.LocalDate;

public class Prescription {
    private int id;
    private Doctor doctor;
    private Patient patient;
    private Medication medication;
    private LocalDate prescriptionExpiry;
    private String dose; // Added dose field as per Sarah's sticky note

    // Constructor
    public Prescription(int id, Doctor doctor, Patient patient, Medication medication, LocalDate prescriptionExpiry,
            String dose) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.prescriptionExpiry = prescriptionExpiry;
        this.dose = dose;
    }

    // Getters
    public int getId() {
        return id;
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

    public LocalDate getPrescriptionExpiry() {
        return prescriptionExpiry;
    }

    public String getDose() {
        return dose;
    }

    // Setters
    public void setPrescriptionExpiry(LocalDate prescriptionExpiry) {
        this.prescriptionExpiry = prescriptionExpiry;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    // toString method
    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", medication=" + medication +
                ", prescriptionExpiry=" + prescriptionExpiry +
                ", dose='" + dose + '\'' +
                '}';
    }
}
