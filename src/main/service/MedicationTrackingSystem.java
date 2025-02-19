package main.service;
import main.model.Doctor;
import main.model.Prescription;
import main.model.Medication;
import main.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.time.LocalDate;

public class MedicationTrackingSystem {

    private List<Patient> patients;
    private List<Medication> medications;
    private List<Doctor> doctors;

    private int lastDoctorId;
    private int lastPatientId;
    private int lastMedicationId;
    private int lastPrescriptionId;

    //constructor
    public MedicationTrackingSystem() {
        this.doctors = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.patients = new ArrayList<>();
        initializeIds();
    }

    public MedicationTrackingSystem(List<Patient> patients, List<Medication> medications, List<Doctor> doctors) {
        this.patients = patients;
        this.medications = medications;
        this.doctors = doctors;
        initializeIds();
    }


    //Patients
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
    public void addPatient(Patient patient) {
        patients.add(patient);
    }
    //Doctors
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
    public List<Doctor> getDoctors() {
        return doctors;
    }
    //Medications
    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
    public List<Medication> getMedications() {
        return medications;
    }
    @Override
    private int GenerateId (int id) {
        int currentYear = LocalDate.now().getYear();
        String currentMonthStr = String.format("%02d", LocalDate.now().getMonth().getValue());
        int currentMonth = Integer.parseInt(currentMonthStr);
        int  idFormat= (currentYear * 1000000)+ (currentMonth * 1000);
        if ((id/1000) == (idFormat/1000)) {
            return id += 1;
        } else {
            return id += 1;
        }
    }
    private void initializeIds() {
        this.lastDoctorId = doctors.stream()
                .mapToInt(Doctor::getId)
                .max()
                .orElse(0);
        this.lastPatientId = patients.stream()
                .mapToInt(Patient::getId)
                .max()
                .orElse(0);
        this.lastMedicationId = medications.stream()
                .mapToInt(Medication::getId)
                .max()
                .orElse(0);
        this.lastPrescriptionId = patients.stream()
                .flatMap(patient -> patient.getPrescriptions().stream())
                .mapToInt(Prescription::getId)
                .max()
                .orElse(0);
    }

    public String toString() {
        return "MedicationTrackingSystem{" +
                "patients=" + patients +
                ", medications=" + medications +
                ", doctors=" + doctors +
                '}';
    }
}

