package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Patient extends Person {
    private List<Medication> medications;
    private List<Prescription> prescriptions;

    // Constructor
    public Patient(String lastName, String firstName, String birthdateStr, String phoneNumber, List<Medication> medications, List<Prescription> prescriptions) {
        super(lastName, firstName, birthdateStr, phoneNumber);  // Call to superclass (main.model.Person) constructor
        this.medications = medications;
        this.prescriptions = prescriptions;
    }
    public Patient(String lastName, String firstName, String birthdateStr, String phoneNumber) {
        super(lastName, firstName, birthdateStr, phoneNumber);  // Call to superclass (main.model.Person) constructor
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }
    public Patient(Person person, Medication medications, Prescription prescriptions) {
        super(person);  // Call to superclass (main.model.Person) constructor
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }
    //copyConstructor
    public Patient(Patient otherPatient) {
        super(otherPatient);
        this.medications = otherPatient.medications;
        this.prescriptions = otherPatient.prescriptions;
    }

    // Getters and Setters
    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

//    public void setPrescriptions(String prescriptions) {
//        this.prescriptions = prescriptions;
//    }

    // Methods to manipulate the medication and prescription list
    public void addMedication(Medication medication) {
        medications.add(medication);
    }
    public void removeMedication(Medication medication) {
        medications.remove(medication);
    }
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }
    public void removePrescription(Prescription prescription) {
        prescriptions.remove(prescription);
    }
    public void clearPrescriptions() {
        this.prescriptions = new ArrayList<>();
    }



    public int getNumberOfPrescriptions() {
        return prescriptions.size();
    }


    //Method to get a sorted list of prescription / medication names

    public List<String> getMedicationList() {
        // Create a shallow copy of the list to avoid modifying the original
        List<Medication> sortedMedications = new ArrayList<>(medications);
        // Sort by medication name
        sortedMedications.sort(Comparator.comparing(Medication::getName));

        // Create an ArrayList to store the medication names with doses
        List<String> medicationList = new ArrayList<>();
        for (Medication medication : sortedMedications) {
            medicationList.add(medication.getName() + ", " + medication.getDose());
        }
        return Collections.unmodifiableList(medicationList);
    }


    public List<String> getPrescriptionList() {
        // Create a shallow copy of the list to avoid modifying the original
        List<Prescription> sortedPrescriptions = new ArrayList<>(prescriptions);
        // Sort prescriptions by medication name using Comparator
        sortedPrescriptions.sort(Comparator.comparing(prescription -> prescription.getMedication().getName()));

        // Create an ArrayList to store the prescription details
        List<String> prescriptionList = new ArrayList<>();
        for (Prescription prescription : sortedPrescriptions) {
            // Assuming you want to show the medication name and patient name
            prescriptionList.add(prescription.getMedication().getName() + " prescribed date " + prescription.getPrescriptionDate());
        }
        return Collections.unmodifiableList(prescriptionList);
    }



    // toString method
    @Override
    public String toString() {
        return super.toString() + ", Medications: " + getMedicationList() + ", Prescriptions: " + getPrescriptionList();
    }
}
