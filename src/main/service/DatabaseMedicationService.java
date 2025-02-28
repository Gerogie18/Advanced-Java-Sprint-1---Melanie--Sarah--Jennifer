package main.service;

import main.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseMedicationService implements MedicationService {
    private List<Medication> medications;
    private List<Prescription> prescriptions;

    // constructor
    public DatabaseMedicationService() {
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public DatabaseMedicationService(List<Medication> medications, List<Prescription> prescriptions) {
        this.medications = new ArrayList<>(medications);
        this.prescriptions = new ArrayList<>(prescriptions);
    }

    public DatabaseMedicationService(List<Medication> medications) {
        this.medications = new ArrayList<>(medications);
        this.prescriptions = new ArrayList<>();
    }

    public List<Medication> getAllMedications() {
        return medications;
    }

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

    public void updateMedication(Medication medication, String newName, String newDose) {
        medication.setName(newName);
        medication.setDose(newDose);
    }

    // - Search for medications by name or ID
    public Optional<Medication> getMedicationById(int id) {
        return medications.stream()
                .filter(medication -> medication.getId() == id)
                .findFirst();
    }

    public List<Medication> searchMedicationsByName(String name) {
        return medications.stream()
                .filter(medication -> medication.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> getFilterdList(String name) {
        return medications.stream()
                .filter(medications -> medications.getName().equalsIgnoreCase(name))
                .map(medications -> medications.toString() + " ID: " + medications.getId()) // Append ID to the toString
                                                                                            // result
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

    public List<Prescription> getAllPrescriptions() {
        return prescriptions;
    }

    public Optional<Prescription> getPrescriptionById(int id) {
        return prescriptions.stream()
                .filter(prescription -> prescription.getId() == id)
                .findFirst();
    }

    // - Allow doctors to prescribe medications to patients
    public Prescription createPrescription(Doctor doctor, Patient patient, Medication medication, String instructions) {
        if (doctor == null || patient == null || medication == null || instructions == null) {
            throw new IllegalArgumentException("Doctor, patient, medication, and label instructions must not be null");
        }
        Prescription prescription = new Prescription(doctor, patient, medication, instructions);

        prescriptions.add(prescription);
        patient.addPrescription(prescription);
        return prescription;
    }

    public Prescription updatePrescription(Prescription prescription, String newDose, String instructions) {
        Prescription newPrescription = new Prescription(prescription);
        newPrescription.getMedication().setDose(newDose);
        newPrescription.setInstructions(instructions);
        return newPrescription;
    }

    public Prescription renewPrescription(Prescription prescription) {
        return new Prescription(prescription);
    }

    // - View prescriptions for a doctor
    public List<Prescription> getPrescriptionsByDoctor(Doctor doctor) {
        return prescriptions.stream()
                .filter(prescription -> prescription.getDoctor().equals(doctor))
                .collect(Collectors.toList());
    }

    // Reporting and Alerts
    // ---------------------
    // - Check for expired medications
    public List<Medication> getExpiredMedications() {
        return medications.stream()
                .filter(Medication::isExpired)
                .collect(Collectors.toList());
    }

    // - check for expired prescriptions
    public List<Prescription> getExpiredPrescriptions() {
        return prescriptions.stream()
                .filter(Prescription::isExpired)
                .collect(Collectors.toList());
    }
    // - Print a list of all prescriptions issued by a specific doctor
    // public List<Prescription> returnPrescriptionsByDoctor(Doctor doctor) {
    // return patients.stream()
    // .flatMap(patient -> patient.getPrescriptions().stream())
    // .filter(prescription -> prescription.getDoctor().equals(doctor))
    // .collect(Collectors.toList());
    // }

    public void restockAllMedications(List<Medication> medications, int max) {
        for (Medication medication : medications) {
            medication.setStockQuantity(max); //
        }
    }

}