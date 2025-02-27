package main.service;

import main.model.Patient;
import main.model.Prescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabasePatientService implements PatientService {
    private List<Patient> patients;

    // constructor
    public DatabasePatientService() {
        this.patients = new ArrayList<>();
    }

    public DatabasePatientService(List<Patient> patients) {
        this.patients = patients;
    }

    // Patient Management
    // -------------------
    public List<Patient> getAllPatients() {
        return patients;
    }

    // - Add patients to the system
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // - Remove patients from the system
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    public void removePatient(int patientId) {
        patients.removeIf(patient -> patient.getId() == patientId);
    }

    // - Search for patients by name or ID
    public Optional<Patient> getPatientById(int id) {
        return patients.stream()
                .filter(patient -> patient.getId() == id)
                .findFirst();
    }
    // public List<Patient> searchPatientsById(int id) {
    // return patients.stream()
    // .filter(patient -> patient.getId() == id)
    // .collect(Collectors.toList());
    // }

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

    public List<String> getFilterdList(String firstName, String lastName) {
        return patients.stream()
                .filter(Patient -> Patient.getFirstName().equalsIgnoreCase(firstName)
                        || Patient.getLastName().equalsIgnoreCase(lastName))
                .map(patient -> patient.toString() + " ID: " + patient.getId()) // Append ID to the toString result
                .collect(Collectors.toList());
    }

    // - View patient details (including prescriptions)
    public List<Prescription> getPrescriptionsByPatient(Patient patient) {
        return patient.getPrescriptions();
    }

    public List<Prescription> getPrescriptionsByPatientId(int id) {
        // Attempt to find the patient and return their prescriptions
        Optional<Patient> verifiedPatient = getPatientById(id);
        if (verifiedPatient.isPresent()) {
            return verifiedPatient.get().getPrescriptions();
        } else {
            throw new IllegalArgumentException("Patient not found.");
        }
    }

}
