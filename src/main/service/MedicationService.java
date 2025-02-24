package main.service;

import main.model.*;

import java.util.List;

public interface MedicationService {
    void addMedication(Medication medication);
    void removeMedication(Medication medication);
    void removeMedicationById(int medicationId);
    void updateMedication(Medication medication, String newName, String newDose);
    List<Medication> searchMedicationsById(int id);
    List<Medication> searchMedicationsByName(String name);
    void adjustMedicationInventory(Medication medication, int adjustment);

   // Prescription Management
    // - Allow doctors to prescribe medications to patients
    Prescription createPrescription(Doctor doctor, Patient patient, Medication medication);
    Prescription renewPrescription(Prescription prescription, String dose);
//    List<Prescription> getPrescriptionsByPatient(int id);
    List<Prescription> getPrescriptionsByDoctor(Doctor doctor);


    // Reporting and Alerts
    List<Medication> getExpiredMedications();
    List<Prescription> getExpiredPrescriptions();
//    List<Prescription> returnPrescriptionsByDoctor(Doctor doctor);
    // - formatList
//    <T> String formatList(List<T> list);
//    // - Generate a report of all system data
//    String generateFullReport();
}
