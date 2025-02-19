package main.service;/*
import main.model.Doctor;
import main.model.Medication;
import main.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MedicationTrackingSystem {

    private List<Patient> patients;
    private List<Medication> medications;
    private List<Doctor> doctors;

    //constructor
    public MedicationTrackingSystem() {
        this.doctors = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.patients = new ArrayList<>();
    }

    public MedicationTrackingSystem(List<Patient> patients, List<Medication> medications, List<Doctor> doctors) {
        this.patients = patients;
        this.medications = medications;
        this.doctors = doctors;
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
    public String toString() {
        return "MedicationTrackingSystem{" +
                "patients=" + patients +
                ", medications=" + medications +
                ", doctors=" + doctors +
                '}';
    }
}

