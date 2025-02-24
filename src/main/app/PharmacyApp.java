package main.app;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import main.model.Patient;
import main.model.Prescription;
import main.model.Doctor;
import main.model.Medication;

import main.service.PatientService;
import main.service.DoctorService;
import main.service.MedicationService;

import main.service.DatabasePatientService;
import main.service.DatabaseDoctorService;
import main.service.DatabaseMedicationService;

public class PharmacyApp {
    // Declare service references using the interface types
    private static PatientService patientService;
    private static DoctorService doctorService;
    private static MedicationService medicationService;

    public static void main(String[] args) {
        // Initialize services using their concrete implementations
        patientService = new DatabasePatientService();
        doctorService = new DatabaseDoctorService();
        medicationService = new DatabaseMedicationService();

        boolean exit = false;

        while (!exit){
            Scanner scanner = new Scanner(System.in);
            System.out.println("=====Welcome To The Pharmacy Med Tracking System=====");
            System.out.println("Menu Options: ");
            System.out.println("1: Add a new patient");
            System.out.println("2: Add a new doctor");
            System.out.println("3: Add a new medication to the pharmacy");
            System.out.println("4: Print System Report");
            System.out.println("5: Check If Meds Are Expired");
            System.out.println("6: Process a new prescription");
            System.out.println("7: Print all scripts for a specific doctor");
//            System.out.println("8: Restock the drugs in the pharmacy");
            System.out.println("9: Print all scripts for specific patient");
            System.out.println("10: Exit");
            System.out.print("What would you like to do? ");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    addAPatient(scanner);
                    break;
                case 2:
                    addADoctor(scanner);
                    break;
                case 3:
                    addAMedicationToPharmacy(scanner);
                    break;
                case 6:
                    updatePatientInformation(scanner);
                    break;
                case 5:
                    deleteAPatient(scanner);
                    break;
                case 4:
                    printPharmacyReport(scanner);
                    break;
//                case 5:
//                    checkExpiredMeds(scanner);
//                    break;
//                case 6:
//                    processANewScript(scanner);
//                    break;
//                case 7:
//                    printScriptsForSpecificDoctor(scanner,system);
//                    break;
////                case 8:
//                    restockPharmacyDrugs(scanner,system);
//                    break;
                case 9:
                    printAllScriptsForPatientByName(scanner);
                    break;
                case 10:
                    exit = true;
                    System.out.println("Exiting The System! Good Bye!");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }

    private static void updatePatientInformation(Scanner scanner) {
        System.out.print("Enter patient ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        System.out.print("New First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("New Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("New Phone Number: ");
        String phoneNumber = scanner.nextLine();

        // verify the patient exists
        Optional<Patient> verifiedPatient = patientService.getPatientById(id);
        verifiedPatient.ifPresent(patient -> {
            patient.setFirstName(firstName);
            patient.setLastName(lastName);
            patient.setPhoneNumber(phoneNumber);
            System.out.println("Patient updated successfully.");
        });
        if (verifiedPatient.isEmpty()) {
            System.out.println("Patient not found.");
        }
    }

    private static void deleteAPatient(Scanner scanner) {
        System.out.print("Enter patient ID to delete: ");
        int id = scanner.nextInt();
        patientService.removePatient(id);
        System.out.println("Patient deleted successfully.");
    }

    private static void printAllScriptsForPatientByName(Scanner scanner) {
//        String firstName;
//        String lastName;
//        scanner.nextLine();
//        System.out.println("Please enter the following information: ");
//        System.out.print("First Name: ");
//        firstName = scanner.nextLine();
//
//        System.out.print("Last Name: ");
//        lastName = scanner.nextLine();
//        List<Patient> patients = service.searchPatientsByLastName(lastName);
//        List<Prescription> scriptList = service.getPrescriptionsByPatient(Patient patients[0]);

        System.out.println("scriptList");

    }
//
//    private static void restockPharmacyDrugs(Scanner scanner, MedicationTracking system) {
//
//    }
//
//    private static void printScriptsForSpecificDoctor(Scanner scanner, MedicationTracking system) {
//
//    }
//
//    private static void processANewScript(Scanner scanner, MedicationTracking system) {
//
//    }
//
//    private static void checkExpiredMeds(MedicationTracking system) {
//
//    }

//    private static void printPharmacyReport(MedicationTracking system) {
//
//    }
//
    private static void addAMedicationToPharmacy(Scanner scanner) {

    }

    private static void addADoctor(Scanner scanner) {
    }
    //    private static void addAPatient(Scanner scanner) {
    private static void addAPatient(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Please enter the following information: ");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Birthday (YYYY-MM-DD): ");
        String birthdateStr = scanner.nextLine();

        System.out.print("Phone Number ((999) 999-9999): ");
        String phoneNumber = scanner.nextLine();


        Patient patient = new Patient(firstName, lastName, birthdateStr, phoneNumber);
        patientService.addPatient(patient);
        System.out.println("New patient added with id: "+ patient.getId());


    }
//
//    public void addPatientToDoctor(Patient patient, Doctor doctor) {
//        doctor.addPatient(patient);
//    }
//
//    public void acceptPrescription(Doctor doctor, Patient patient, Medication medication, String dosage) {
//        Prescription prescription = new Prescription(doctor, patient, medication, dosage);
//        patient.addPrescription(prescription);
//        medicationService.addPrescription(prescription); // Assuming there's a method to add prescriptions
//    }
//
//    public void editMedication(Medication medication, String newName, String newDose) {
//        medication.setName(newName);
//        medication.setDose(newDose);
//        medicationService.updateMedication(medication);
//    }
//
//
//    public void deleteMedication(Medication medication) {
//        medicationService.removeMedication(medication);
//    }

    public void deletePatient(int patientId) {
//        int patientId = patient.getId();
        patientService.removePatient(patientId);
    }

//    public void deleteDoctor(Doctor doctor) {
//        doctorService.removeDoctor(doctor);
//    }


    // - formatList
    public static <T> String formatList(List<T> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
    // - Generate a report of all system data
    public static String printPharmacyReport(Scanner scanner) {
        List<Patient> patients = patientService.getAllPatients();
        List<Medication> medications = medicationService.getAllMedications();
        List<Doctor> doctors = doctorService.getAllDoctors();

        String report = """
        Patients:
        %s
        
        Medications:
        %s
        
        Doctors:
        %s
        """.formatted(formatList(patients), formatList(medications), formatList(doctors));
        System.out.println("New patient added with id: "+ report);
        return report;
    }

}

