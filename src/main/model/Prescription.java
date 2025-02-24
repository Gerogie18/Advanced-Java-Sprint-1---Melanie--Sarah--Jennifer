package main.model;

import java.time.LocalDate;

public class Prescription {
    private static int nextId = 40000001; // Static variable to keep track of the next ID to be assigned

    private final int id;
    private final Doctor doctor;
    private final Patient patient;
    private final Medication medication;
    private final LocalDate date;

    // Constructor
    public Prescription(Doctor doctor, Patient patient, Medication medication) {
        this.id = nextId++;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.date = LocalDate.now();
    }
    public Prescription(Prescription other) {
        this.id = nextId++;
        this.doctor = other.doctor;
        this.patient = other.patient;
        this.medication = other.medication;
        this.date = LocalDate.now();
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

    public LocalDate getPrescriptionDate() {
        return date;
    }

    public LocalDate getPrescriptionExpiry() {
        return date.plusYears(1);
    }


    public boolean isExpired() {
        return LocalDate.now().isAfter(getPrescriptionExpiry());
    }

    // toString method
    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", doctor= Dr. " + doctor.getFullName() +
                ", patient= " + patient.getFullName() +
                ", medication= " + medication.getName() +
                ", prescriptionExpiry= " + date +
                '}';
    }
}
