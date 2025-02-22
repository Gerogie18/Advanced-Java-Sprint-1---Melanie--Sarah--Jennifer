package main.service;

import main.model.Doctor;
import main.model.Prescription;
import main.model.Medication;
import main.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class MedicationTrackingSystem {

    private List<Patient> patients;
    private List<Medication> medications;
    private List<Doctor> doctors;

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
        initializeLastPersonId();
        initializeLastPrescriptionId();
    }
    public List<Patient> getPatients() {
        return patients;
    }
    public void addPatient(Patient patient) {
        patients.add(patient);
    }
    public void addPrescriptionToPatient(Patient patient, Prescription prescription) {
        patient.addPrescription(prescription);
    }
    public void addPrescriptionToPatient(int patientId, Prescription prescription) {
        Patient patient = searchPatientsById(patientId).get(0);
        patient.addPrescription(prescription);
    }
    public void addPrescriptionToPatient(int patientId, Prescription prescription) {
        Patient patient = searchPatientsById(patientId).get(0);
        patient.addPrescription(prescription);
    }
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }
    public void removePatient(int patientId) {
        patients.removeIf(patient-> patient.getId() == patientId);
    }
    public List<Patient> searchPatientsById(int id) {
        return patients.stream()
                .filter(patient -> patient.getId() == id)
                .collect(Collectors.toList());
    }
    public List<Patient> searchPatientsByFirstName(String firstName) {
        return patients.stream()
                .filter(patient -> patient.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Patient> searchPatientsByLastName(String lastName) {
        return patients.stream()
                .filter(patient -> patient.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Prescription> getPrescriptionsByPatient(Patient patient) {
        return patient.getPrescriptions();
    }

    public List<Prescription> getPrescriptionsByPatient(int id) {
        Patient patient = searchPatientsById(id).get(0);
        return patient.getPrescriptions();
    }
    public List<Prescription> getPrescriptionsByDoctor(Doctor doctor) {
        return patients.stream()
                .flatMap(patient -> patient.getPrescriptions().stream())
                .filter(prescription -> prescription.getDoctorId() == doctor.getId())
                .collect(Collectors.toList());
    }
    public List<Prescription> getPrescriptionsByDoctorId(int id) {
        return patients.stream()
                .flatMap(patient -> patient.getPrescriptions().stream())
                .filter(prescription -> prescription.getDoctorId() == id)
                .collect(Collectors.toList());
    }


    //Doctors
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
        initializeLastPersonId();
    }
    public List<Doctor> getDoctors() {
        return doctors;
    }
    public void addDoctor(Doctor doctor) {
        this.lastDoctorId = GenerateId(lastDoctorId);
        doctor.setId(lastDoctorId);
        doctors.add(doctor);
    }
    public void addPatientToDoctor(Patient patient, Doctor doctor) {
        doctor.addPatient(patient);
    }
    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }
    public void removeDoctor(int doctorId) {
        doctors.removeIf(doctor -> doctor.getId() == doctorId);
    }
    public List<Doctor> searchDoctorsId(int id) {
        return doctors.stream()
                .filter(doctor -> doctor.getId() == id)
                .collect(Collectors.toList());
    }
    public List<Doctor> searchDoctorsFirstName(String firstName) {
        return doctors.stream()
                .filter(doctor -> doctor.getFirstName() == firstName)
                .collect(Collectors.toList());
    }
    public List<Doctor> searchDoctorsByLastName(String lastName) {
        return doctors.stream()
                .filter(doctor -> doctor.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Doctor> searchDoctorsBySpecialty(String specialty) {
        return doctors.stream()
                .filter(doctor -> doctor.getSpecialty().toLowerCase().contains(specialty.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Doctor> searchDoctorsByPatientId(int patientId) {
        return doctors.stream()
                .filter(doctor -> doctor.getPatients().stream().anyMatch(patient -> patient.getId() == patientId))
                .collect(Collectors.toList());
    }

    //Medications
    public void setMedications(List<Medication> medications) {
        this.medications = medications;
        initializeLastMedicationId();
    }
    public List<Medication> getMedications() {
        return medications;
    }
    public void addMedication(Medication medication) {
        medications.add(medication);
    }
    public void removeMedication(Medication medication) {
        medications.remove(medication);
    }
    public void removeMedication(int medicationId) {
        medications.removeIf(medication -> medication.getId() == medicationId);
    }
    public List<Medication> searchMedicationsById(int id) {
        return medications.stream()
                .filter(medication -> medication.getId() == id)
                .collect(Collectors.toList());
    }

    public List<Medication> searchMedicationsByName(String name) {
        return medications.stream()
                .filter(medication -> medication.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void adjustMedicationInventory(Medication medication, int adjustment) {
        int newQuantity = medication.getStockQuantity() + adjustment;
        if (newQuantity < 0) {
            throw new RuntimeException("Cannot adjust inventory to a negative quantity");
        }
        medication.setStockQuantity(newQuantity);
    }
    public void fillPrescription(Patient patient, Prescription prescription) {
        patient.removePrescription(prescription);

        Medication medication = searchMedicationsById(prescription.getMedicationId()).get(0);
        int newQuantity = medication.getStockQuantity() - prescription.getQuantity();
        if (newQuantity < 0) {
            throw new RuntimeException("Insufficient medication quantity");
        }
        medication.setStockQuantity(newQuantity);
        
        patient.addMedication(medication, prescription.getQuantity());
    }

    //utils
    public boolean validateSystem() {
        // Assuming you have lists of IDs for patients, doctors, medications, and prescriptions
        List<String> patientIds = patients.stream().map(Patient::getId).collect(Collectors.toList());
        List<String> doctorIds = doctors.stream().map(Doctor::getId).collect(Collectors.toList());
        List<String> medicationIds = medications.stream().map(Medication::getId).collect(Collectors.toList());
        List<Prescription> prescriptions = patients.stream()
                .flatMap(patient -> patient.getPrescriptions().stream())
                .collect(Collectors.toList());
        List<String> prescriptionIds = prescriptions.stream().map(Prescription::getId).collect(Collectors.toList());

        // Check for duplicates in each list
        return !(hasDuplicates(patientIds) || hasDuplicates(doctorIds) || hasDuplicates(medicationIds) || hasDuplicates(prescriptionIds));
    }
    private boolean hasDuplicates(List<String> ids) {
        return ids.size() != new HashSet<>(ids).size();
    }


    private void initializeIds() {
        this.LastPersonID = initializeLastPersonId();
        this.lastMedicationId = initializeLastMedicationId();
        this.lastPrescriptionId = initializeLastPrescriptionId();
    }

    private int initializeLastPersonId(List<? extends Person> people) {
        return people.stream()
                .mapToInt(Person::getId)
                .max()
                .orElse(0);
    }

    private int initializeLastMedicationId() {
        return medications.stream()
                .mapToInt(Medication::getId)
                .max()
                .orElse(0);
    }

    private int initializeLastPrescriptionId() {
        return patients.stream()
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

