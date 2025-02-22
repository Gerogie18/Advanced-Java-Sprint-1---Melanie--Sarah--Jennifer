package main.model;

import java.time.LocalDate;

public class Prescription {
    private static int nextId = 40000001; // Static variable to keep track of the next ID to be assigned

    private final int id;
    private final Doctor doctor;
    private final Patient patient;
    private final Medication medication;
    private LocalDate prescriptionExpiry;

    // Constructor
    public Prescription(Doctor doctor, Patient patient, Medication medication) {
        this.id = nextId++;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.prescriptionExpiry = LocalDate.now().plusYears(1);
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

    // Setters
    public void setPrescriptionExpiry(LocalDate prescriptionExpiry) {
        this.prescriptionExpiry = prescriptionExpiry;
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
                '}';
    }
}
