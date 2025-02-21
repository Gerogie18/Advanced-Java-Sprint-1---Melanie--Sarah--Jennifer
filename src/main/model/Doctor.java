package main.model;
//import main.model.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Doctor extends Person {
        private static int ID_OFFSET = 4000;

        private final int doctorId;
        private String specialization;
        private List<Patient> patients;

        // Constructor
        public Doctor(String lastName, String firstName, String birthdateStr, String phoneNumber, String specialization, List<Patient> patients) {
            super(lastName, firstName, birthdateStr, phoneNumber);  // Call to superclass (Person) constructor
            this.doctorId = super.getId() + ID_OFFSET;
            this.specialization = specialization;
            this.patients = new ArrayList<>();
        }
        
        public Doctor(Person person, String specialization, List<Patient> patients) {
            super(lastName, firstName, birthdateStr, phoneNumber);  // Call to superclass (Person) constructor
            this.doctorId = super.getId() + ID_OFFSET;
            this.specialization = specialization;
            this.patients = new ArrayList<>();
        }
        //copyConstructor
        public Doctor(Doctor otherDoctor) {
            super(otherDoctor);
            this.doctorId = super.getId() + ID_OFFSET;// Call to superclass (Person) constructor
            this.specialization = otherDoctor.specialization;
            this.patients = otherDoctor.patients;
        }

        // Getters and Setters
        public int getDocId() {
            return doctorId;
        }

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }
        // return a viewable list of patients but prevents the list from being changed
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

            // Sort the new list based on the last name
            // Note Patient::getFirstName === (Patient p) -> p.getFirstName().
            // This is sensitive to case
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

        // toString method
        @Override
        public String toString() {
            return super.toString() + ", specialization: " + specialization + ", patients: " + getPatientList();
        }
    }
