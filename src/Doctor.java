import java.util.Arrays;

public class Doctor extends Person{
        private String specialization;
        private Patient[] patients;

        // Constructor
        public Doctor(String lastName, String firstName, String birthdateStr, String phoneNumber, String specialization, Patient[] patients) {
            super(lastName, firstName, birthdateStr, phoneNumber);  // Call to superclass (Person) constructor
            this.specialization = specialization;
            this.patients = patients;
        }

        //copyConstructor
        public Doctor(Doctor otherDoctor) {
            super(otherDoctor);  // Call to superclass (Person) constructor
            this.specialization = otherDoctor.specialization;
            this.patients = otherDoctor.patients;
        }

        // Getters and Setters
        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }

        public Patient[] getPatients() {
            return patients;
        }

        // Method to get a sorted list of patient names
        public String[] getPatientList() {
            // Clone the array to avoid modifying the original array
            Patient[] sortedPatients = patients.clone();

            // Sort the cloned array based on the last name
            Arrays.sort(sortedPatients, (p1, p2) -> p1.getLastName().compareToIgnoreCase(p2.getLastName()));

            // Create a String array to store the names
            String[] patientList = new String[sortedPatients.length];
            for (int i = 0; i < sortedPatients.length; i++) {
                patientList[i] = sortedPatients[i].getFirstName() + " " + sortedPatients[i].getLastName();
            }
            return patientList;
        }
        public void setPatients(Patient[] patients) {
            this.patients = patients;
        }

        // toString method
        @Override
        public String toString() {
            return super.toString() + ", specialization: " + specialization + ", patients: " + Arrays.toString(getPatientList());
        }
    }
