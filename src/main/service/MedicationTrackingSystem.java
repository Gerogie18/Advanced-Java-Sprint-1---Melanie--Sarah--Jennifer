package main.service;

import main.model.Doctor;
import main.model.Prescription;
import main.model.Medication;
import main.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicationTrackingSystem {

    private final List<Patient> patients;
    private final List<Medication> medications;
    private final List<Doctor> doctors;

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
    // Patient Management
    // -------------------
    // - Add patients to the system
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // - Remove patients from the system
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }
    public void removePatientById(int patientId) {
        patients.removeIf(patient -> patient.getId() == patientId);
    }

    // - Search for patients by name or ID
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

    // - View patient details (including prescriptions)
    public List<Prescription> getPrescriptionsByPatient(Patient patient) {
        return patient.getPrescriptions();
    }

    public List<Prescription> getPrescriptionsByPatientId(int id) {
        Patient patient = searchPatientsById(id).getFirst();
        return patient.getPrescriptions();
    }

    // Doctor Management
    // -----------------
    // - Add doctors to the system
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    // - Remove doctors from the system
    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    public void removeDoctorById(int doctorId) {
        doctors.removeIf(doctor -> doctor.getId() == doctorId);
    }

    // - Search for doctors by name or ID
    public List<Doctor> searchDoctorsId(int id) {
        return doctors.stream()
                .filter(doctor -> doctor.getId() == id)
                .collect(Collectors.toList());
    }

    public List<Doctor> searchDoctorsFirstName(String firstName) {
        return doctors.stream()
                .filter(doctor -> doctor.getFirstName().equals(firstName))
                .collect(Collectors.toList());
    }

    public List<Doctor> searchDoctorsByLastName(String lastName) {
        return doctors.stream()
                .filter(doctor -> doctor.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Doctor> searchDoctorsBySpecialty(String specialization) {
        return doctors.stream()
                .filter(doctor -> doctor.getSpecialization().toLowerCase().contains(specialization.toLowerCase()))
                .collect(Collectors.toList());
    }

    // - View doctor details (including patients and prescriptions)
    // Medication Management
    // ---------------------
    // - Add medications to the system
    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    // - Remove medications from the system
    public void removeMedication(Medication medication) {
        medications.remove(medication);
    }

    public void removeMedicationById(int medicationId) {
        medications.removeIf(medication -> medication.getId() == medicationId);
    }

    // - Search for medications by name or ID
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

    // - Restock medications
    public void adjustMedicationInventory(Medication medication, int adjustment) {
        int newQuantity = medication.getStockQuantity() + adjustment;
        if (newQuantity < 0) {
            throw new RuntimeException("Cannot adjust inventory to a negative quantity");
        }
        medication.setStockQuantity(newQuantity);
    }

    // Prescription Management
    // ----------------------
    // - Allow doctors to prescribe medications to patients
    public void createPrescription(Medication medication, Patient patient, Doctor doctor) {
        Prescription prescription = new Prescription(doctor, patient, medication);
        patient.addPrescription(prescription);
    }

    public List<Prescription> getPrescriptionsByPatient(int id) {
        Patient patient = searchPatientsById(id).getFirst();
        return patient.getPrescriptions();
    }

    // - View prescriptions for a doctor
    public List<Prescription> getPrescriptionsByDoctor(Doctor doctor) {
        return patients.stream()
                .flatMap(patient -> patient.getPrescriptions().stream())
                .filter(prescription -> prescription.getDoctor().equals(doctor))
                .collect(Collectors.toList());
    }
    // Reporting and Alerts
    // ---------------------
    // - Check for expired medications
    public List<Medication> returnExpiredMedications() {
        return medications.stream()
                .filter(Medication::isExpired)
                .collect(Collectors.toList());
    }
    // - check for expired prescriptions
    public List<Prescription> returnExpiredPrescriptions() {
        return patients.stream()
                .flatMap(patient -> patient.getPrescriptions().stream())
                .filter(prescription -> prescription.isExpired())
                .collect(Collectors.toList());
    }
    // - Print a list of all prescriptions issued by a specific doctor
    public List<Prescription> returnPrescriptionsByDoctor(Doctor doctor) {
        return patients.stream()
                .flatMap(patient -> patient.getPrescriptions().stream())
                .filter(prescription -> prescription.getDoctor().equals(doctor))
                .collect(Collectors.toList());
    }
    // - formatList
    public <T> String formatList(List<T> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
    // - Generate a report of all system data
    public String formatSystemData() {
        return """
        Patients:
        %s
        
        Medications:
        %s
        
        Doctors:
        %s
        """.formatted(formatList(patients), formatList(medications), formatList(doctors));
    }

}

