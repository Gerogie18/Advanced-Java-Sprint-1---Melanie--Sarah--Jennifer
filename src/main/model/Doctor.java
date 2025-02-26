package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Doctor extends Person {

        private String specialization;
        private List<Patient> patients;
        private boolean isTakingPatients;


    public interface DoctorConstants {
        int ID_OFFSET = 10000000;
    }
        // Constructor
    public Doctor(String lastName, String firstName, String birthdateStr, String phoneNumber, String specialization, List<Patient> patients) {
        super(lastName, firstName, birthdateStr, phoneNumber, DoctorConstants.ID_OFFSET);
        this.specialization = specialization;
        this.patients = new ArrayList<>(patients);
        this.isTakingPatients = true;
    }

    public Doctor(String lastName, String firstName, String birthdateStr, String phoneNumber, String specialization) {
        super(lastName, firstName, birthdateStr, phoneNumber, DoctorConstants.ID_OFFSET);
        this.specialization = specialization;
        this.patients = new ArrayList<>();
        this.isTakingPatients = true;
    }


        //copyConstructor
        public Doctor(Doctor otherDoctor) {
            super(otherDoctor.getLastName(), otherDoctor.getFirstName(), otherDoctor.getBirthdate().toString(), otherDoctor.getPhoneNumber(), DoctorConstants.ID_OFFSET);
            this.specialization = otherDoctor.specialization;
            this.patients = new ArrayList<>(otherDoctor.patients);
            this.isTakingPatients = true;
        }

        // Getters and Setters
        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }

        public boolean canAcceptPatients() {
            return isTakingPatients;
        }

        public void setIsTakingPatients(boolean takingPatients) {
            this.isTakingPatients = takingPatients;
        }

        public List<Patient> getPatients() {
            return Collections.unmodifiableList(patients);
        }


        // Methods to manipulate the patients list
        public void addPatient(Patient patient) {
            patients.add(patient);
        }

        public void removePatient(Patient patient) {
            patients.remove(patient);
        }

        public void clearPatients() {
            this.patients = new ArrayList<>();
        }

        public int getNumberOfPatients() {
            return patients.size();
        }

        // Method to get a sorted list of patient names
        public List<String> getPatientList() {
            // create shallow copy of list to avoid modifying the original
            List<Patient> sortedPatients = new ArrayList<>(patients);

            Comparator<Patient> byLastNameThenFirstName = Comparator
                    .comparing(Patient::getLastName)
                    .thenComparing(Patient::getFirstName);

            sortedPatients.sort(byLastNameThenFirstName);

            // Create a ArrayList to store the names
            List<String> patientList = new ArrayList<>();
            for (Patient patient : sortedPatients) {
                patientList.add(patient.getLastName() + ", " + patient.getFirstName());
            }
            return Collections.unmodifiableList(patientList);
        }

        @Override
        public String toString() {
            return super.toString() + ", specialization: " + specialization + ", patients: " + getPatientList();
        }
    }
