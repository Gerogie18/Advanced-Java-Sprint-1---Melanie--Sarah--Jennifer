package main.service;

import main.model.*;

import java.util.List;
import java.util.Optional;

public interface MedicationService {

    // Medication Management
    void addMedication(Medication medication);

    void removeMedication(Medication medication);

    void removeMedicationById(int medicationId);

    void updateMedication(Medication medication, String newName, String newDose);

    Optional<Medication> getMedicationById(int id);

    void adjustMedicationInventory(Medication medication, int adjustment);

    void restockAllMedications(List<Medication> medications, int max);

    // Prescription Management
    Prescription createPrescription(Doctor doctor, Patient patient, Medication medication, String instructions);

    Prescription updatePrescription(Prescription prescription, String dose, String instructions);

    Prescription renewPrescription(Prescription prescription);

    Optional<Prescription> getPrescriptionById(int id);

    // Reporting and Alerts
    List<Medication> getAllMedications();

    List<Medication> getExpiredMedications();

    List<Medication> searchMedicationsByName(String name);

    List<String> getFilterdList(String name);

    List<Prescription> getAllPrescriptions();

    List<Prescription> getExpiredPrescriptions();

    List<Prescription> getPrescriptionsByDoctor(Doctor doctor);
}
