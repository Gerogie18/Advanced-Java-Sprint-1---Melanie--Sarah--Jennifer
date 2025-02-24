package main.app;

import java.time.LocalDate;
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
            System.out.println("Main Menu Options: ");
            System.out.println("1: Add/Delete a patient, doctor, or medication");
            System.out.println("2: Edit details for a patient, doctor, or medication");
            System.out.println("3: Search");
            System.out.println("4: Prescription Management");
            System.out.println("5: Generate Reports");
            System.out.println("10: Exit");
            System.out.print("What would you like to do? ");

            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    addDeleteEntitiesMenu(scanner);
                    break;
                case 2:
                    updateDetailsMenu(scanner);
                    break;
                case 3:
                    searchEntitiesMenu(scanner);
                    break;
                case 4:
                    prescriptionManagementMenu(scanner);
                    break;
                case 5:
                    generateReportsMenu(scanner);
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid option, please choose again.");
            }
        }
    }


// Methods for Submenus
    private static void addDeleteEntitiesMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Add Patient");
            System.out.println("2. Delete Patient");
            System.out.println("3. Add Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. Add Medication");
            System.out.println("6. Delete Medication");

            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    addAPatient(scanner);
                    break;
                case 2:
                    deleteAPatient(scanner);
                    break;
                case 3:
                    addADoctor(scanner);
                    break;
                case 4:
                    deleteADoctor(scanner);
                    break;
                case 5:
                    addAMedicationToPharmacy(scanner);
                    break;
                case 6:
                    deleteAMedication(scanner);
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid option, please choose again.");
            }
        }
    }


    private static void updateDetailsMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
        System.out.println("1. Edit Patient");
        System.out.println("2. Edit Doctor");
        System.out.println("3. Edit Medication");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                updatePatientDetails(scanner);
                break;
            case 2:
                updateDoctorDetails(scanner);
                break;
            case 3:
                updateMedicationDetails(scanner);
                break;
            case 4:
                exit = true;
                System.out.println("Exiting the system...");
                break;
            default:
                System.out.println("Invalid option, please choose again.");
            }
        }
    }

    private static void searchEntitiesMenu(Scanner scanner) {
        System.out.println("1. Search Patient");
        System.out.println("2. Search Doctor");
        System.out.println("3. Search Medication");
    }

    private static void prescriptionManagementMenu(Scanner scanner) {
        System.out.println("1. Accept Prescription");
        System.out.println("2. Assign Patient to Doctor");
    }

    private static void generateReportsMenu(Scanner scanner) {
        System.out.println("1. Generate Full System Report");
        System.out.println("2. Check for Expired Medications");
        System.out.println("3. Print Prescriptions for a Doctor");
        System.out.println("4. Restock Drugs");
    }


    // Add/Delete Methods

    private static void addAPatient(Scanner scanner) {
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


    private static void deleteAPatient(Scanner scanner) {
        System.out.print("Enter patient ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        patientService.removePatient(id);
        System.out.println("Patient deleted successfully.");
    }


    private static void addADoctor(Scanner scanner) {
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

        System.out.print("Speciality: ");
        String speciality = scanner.nextLine();

        Doctor doctor = new Doctor(firstName, lastName, birthdateStr, phoneNumber, speciality);
        doctorService.addDoctor(doctor);
        System.out.println("New doctor added with id: "+ doctor.getId());
    }

    private static void deleteADoctor(Scanner scanner) {
        System.out.print("Enter doctor ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        doctorService.removeDoctorById(id);
        System.out.println("Doctor deleted successfully.");
    }

    private static void addAMedicationToPharmacy(Scanner scanner) {
        System.out.println("Please enter the following information: ");
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Dose: ");
        String dose = scanner.nextLine();

        System.out.print("Stock Quantity: ");
        int stockQty = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        System.out.print("Expiry Date: ");
        String expDate = scanner.nextLine();

        Medication medication = new Medication(name, dose, stockQty, LocalDate.parse(expDate));
        medicationService.addMedication(medication);
        System.out.println("New medication added with id: "+ medication.getId());
    }

    private static void deleteAMedication(Scanner scanner) {
        System.out.print("Enter medication ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        medicationService.removeMedicationById(id);
        System.out.println("Medication deleted successfully.");
    }


    // edit details methods

    private static void updatePatientDetails(Scanner scanner) {
        System.out.print("Enter patient ID to update (press return to skip details you do not wish to update): ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        // Get existing patient details first
        Optional<Patient> verifiedPatient = patientService.getPatientById(id);
        if (verifiedPatient.isEmpty()) {
            System.out.println("Patient not found.");
            return; // Exit if no patient found
        }

        Patient patient = verifiedPatient.get();

        // Prompt for first name and check if the user wants to update it
        System.out.print("New First Name: ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) {
            patient.setFirstName(firstName);
        }

        // Prompt for last name and check if the user wants to update it
        System.out.print("New Last Name: ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {
            patient.setLastName(lastName);
        }

        // Prompt for phone number and check if the user wants to update it
        System.out.print("New Phone Number: ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) {
            patient.setPhoneNumber(phoneNumber);
        }

        System.out.println("Patient information updated successfully.");
    }

    private static void updateDoctorDetails(Scanner scanner) {
        System.out.print("Enter Doctor ID to update (press return to skip details you do not wish to update): ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        // Get existing doctor details first
        Optional<Doctor> verifiedDoctor = doctorService.getDoctorById(id);
        if (verifiedDoctor.isEmpty()) {
            System.out.println("Doctor not found.");
            return; // Exit if no doctor found
        }

        Doctor doctor = verifiedDoctor.get();

        // Prompt for first name and check if the user wants to update it
        System.out.print("New First Name: ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) {
            doctor.setFirstName(firstName);
        }

        // Prompt for last name and check if the user wants to update it
        System.out.print("New Last Name: ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {
            doctor.setLastName(lastName);
        }

        // Prompt for phone number and check if the user wants to update it
        System.out.print("New Phone Number: ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) {
            doctor.setPhoneNumber(phoneNumber);
        }

        // Prompt for phone number and check if the user wants to update it
        System.out.print("New Speciality: ");
        String speciality = scanner.nextLine();
        if (!speciality.isEmpty()) {
            doctor.setSpecialization(speciality);
        }
        
        System.out.println("Doctor information updated successfully.");
    }


    private static void updateMedicationDetails(Scanner scanner) {
        System.out.print("Enter Medication ID to update (press return to skip details you do not wish to update): ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        // Get existing doctor details first
        Optional<Medication> verifiedMedication = medicationService.getMedicationById(id);
        if (verifiedMedication.isEmpty()) {
            System.out.println("Medication not found.");
            return; // Exit if no medication found
        }

        Medication medication = verifiedMedication.get();

        // Prompt for first name and check if the user wants to update it
        System.out.print("New Medication Name: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            medication.setName(name);
        }

        // Prompt for last name and check if the user wants to update it
        System.out.print("New Dose: ");
        String dose = scanner.nextLine();
        if (!dose.isEmpty()) {
            medication.setDose(dose);
        }

//        // Prompt for phone number and check if the user wants to update it
//        System.out.print("New Expiry Date: ");
//        String expDate = scanner.nextLine();
//        if (!expDate.isEmpty()) {
//            medication.setExpiryDate(LocalDate.parse(expDate));
//        }

        System.out.println("Medication information updated successfully.");
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
        System.out.println(report);
        return report;
    }

}

