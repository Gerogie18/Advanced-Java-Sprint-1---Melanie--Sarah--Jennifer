package main.util;

import main.model.*;
import java.time.LocalDate;
import java.util.*;

public class TestingDataGenerator {
    private final List<Patient> patients;
    private final List<Doctor> doctors;
    private final List<Medication> medications;

    public TestingDataGenerator() {
        patients = createTestPatients();
        doctors = createTestDoctors();
        medications = createTestMedications();

        assignDoctorsToPatients();
        assignRandomMedication();
        addPrescriptions();
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    private List<Patient> createTestPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("Smith", "John", "1985-06-15", "709-555-0001"));
        patients.add(new Patient("Doe", "Jane", "1992-09-23", "709-555-0002"));
        patients.add(new Patient("Brown", "Charlie", "1978-11-12", "709-555-0003"));
        patients.add(new Patient("Wilson", "Emily", "2001-04-05", "709-555-0004"));
        patients.add(new Patient("Taylor", "Daniel", "1965-02-28", "709-555-0005"));
        patients.add(new Patient("Anderson", "Olivia", "1989-07-19", "709-555-0006"));
        patients.add(new Patient("Thomas", "Michael", "1995-12-30", "709-555-0007"));
        patients.add(new Patient("Jackson", "Sophia", "1973-03-22", "709-555-0008"));
        patients.add(new Patient("White", "Ethan", "2005-08-14", "709-555-0009"));
        patients.add(new Patient("Harris", "Isabella", "1980-10-08", "709-555-0010"));
        patients.add(new Patient("Martin", "Mason", "1998-06-25", "709-555-0011"));
        patients.add(new Patient("Lee", "Ava", "1971-05-17", "709-555-0012"));
        patients.add(new Patient("Walker", "James", "1990-09-02", "709-555-0013"));
        patients.add(new Patient("Allen", "Lily", "1968-12-11", "709-555-0014"));
        patients.add(new Patient("Young", "Benjamin", "1983-07-07", "709-555-0015"));

        return patients;
    }

    private List<Doctor> createTestDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Miller", "Robert", "1975-03-10", "709-555-1001", "Cardiology"));
        doctors.add(new Doctor("Garcia", "Emma", "1980-07-25", "709-555-1002", "Pediatrics"));
        doctors.add(new Doctor("Rodriguez", "David", "1969-05-14", "709-555-1003", "Neurology"));
        return doctors;
    }

    private void assignDoctorsToPatients() {
        int doctorIndex = 0;
        for (Patient patient : patients) {
            doctors.get(doctorIndex).addPatient(patient);
            doctorIndex = (doctorIndex + 1) % doctors.size();
        }
    }

    private List<Medication> createTestMedications() {
        List<Medication> medications = new ArrayList<>();
        medications.add(new Medication("Paracetamol", "500mg", 50, LocalDate.of(2026, 5, 10)));
        medications.add(new Medication("Ibuprofen", "200mg", 30, LocalDate.of(2025, 8, 15)));
        medications.add(new Medication( "Amoxicillin", "250mg", 20, LocalDate.of(2024, 11, 30)));
        medications.add(new Medication( "Cetirizine", "10mg", 40, LocalDate.of(2026, 1, 20)));
        medications.add(new Medication( "Metformin", "500mg", 25, LocalDate.of(2023, 12, 10)));
        medications.add(new Medication( "Aspirin", "75mg", 60, LocalDate.of(2025, 4, 5)));
        medications.add(new Medication( "Lisinopril", "10mg", 35, LocalDate.of(2024, 7, 18)));
        medications.add(new Medication( "Losartan", "50mg", 45, LocalDate.of(2025, 12, 30)));
        medications.add(new Medication( "Atorvastatin", "20mg", 55, LocalDate.of(2026, 3, 25)));
        medications.add(new Medication( "Omeprazole", "20mg", 15, LocalDate.of(2024, 9, 5)));
        return medications;
    }

    private void addPrescriptions() {
        Random random = new Random();
        for (Doctor doctor : doctors) {
            for (Patient patient : doctor.getPatients()) {
                Medication medication = medications.get(random.nextInt(medications.size()));
                Prescription prescription = new Prescription(doctor, patient, medication, "once daily");
                patient.addPrescription(prescription);
            }
        }
    }

    private void assignRandomMedication() {
        Random random = new Random();
        for (Patient patient : patients) {
            Medication randomMedication = medications.get(random.nextInt(medications.size()));
            patient.addMedication(randomMedication);
        }
    }
}
