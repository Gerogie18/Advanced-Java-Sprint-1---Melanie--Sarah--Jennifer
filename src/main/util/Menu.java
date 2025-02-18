package main.util;//package medicationsystem;
import main.model.Patient;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
       // main.service.MedicationSystem medicationTracking = new MedicationTracking();
        boolean exit = false;

        while (!exit){
            Scanner scanner = new Scanner(System.in);
            System.out.println("=====Welcome To The Pharmacy Med Tracking System=====");
            System.out.println("main.util.Menu Options: ");
            System.out.println("1: Add A New main.model.Patient");
            System.out.println("2: Add A New main.model.Doctor");
//            System.out.println("3: Add A New main.model.Medication To The Pharmacy");
//            System.out.println("4: Print System Report");
//            System.out.println("5: Check If Meds Are Expired");
//            System.out.println("6: Process A New main.model.Prescription");
//            System.out.println("7: Print All Scripts For Specific main.model.Doctor");
//            System.out.println("8: Restock the drugs in the pharmacy");
//            System.out.println("9: Print all scripts for specific patient");
            System.out.println("10: Exit");
            System.out.print("What would you like to do? ");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    addANewPatient(scanner);
                    break;
                case 2:
                    addANewDoctor(scanner);
                    break;
//                case 3:
//                    addNewMedicationToPharmacy(scanner, system);
//                    break;
//                case 4:
//                    printPharmacyReport(system);
//                    break;
//                case 5:
//                    checkExpiredMeds(system);
//                    break;
//                case 6:
//                    processANewScript(scanner,system);
//                    break;
//                case 7:
//                    printScriptsForSpecificDoctor(scanner,system);
//                    break;
//                case 8:
//                    restockPharmacyDrugs(scanner,system);
//                    break;
//                case 9:
//                    printAllScriptsForPatientByName(scanner,system);
//                    break;
                case 10:
                    exit = true;
                    System.out.println("Exiting The System! Good Bye!");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }

//    private static void printAllScriptsForPatientByName(Scanner scanner, MedicationTracking system) {
//
//    }
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
//
//    private static void printPharmacyReport(MedicationTracking system) {
//
//    }
//
//    private static void addNewMedicationToPharmacy(Scanner scanner, MedicationTracking system) {
//
//    }

    private static void addANewDoctor(Scanner scanner) {
    }
//    private static void addANewPatient(Scanner scanner, MedicationTracking system) {
    private static void addANewPatient(Scanner scanner) {
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

        System.out.print("Medications: ");
        String medications = scanner.nextLine();

        System.out.print("Prescriptions: ");
        String prescriptions = scanner.nextLine();

        Patient patient = new Patient(firstName, lastName, birthdateStr, phoneNumber, medications, prescriptions);

        System.out.println(patient);

    }

}

