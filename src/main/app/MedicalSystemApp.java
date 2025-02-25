package main.app;
import main.service.*;
import main.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicalSystemApp {
    public static void main(String[] args) {

        // Setup services and dependencies
        Doctor doctor = new Doctor("Doe", "Terry", "1980-01-01", "709-555-0101", "Cardiology");
        Patient patient = new Patient("Smith", "Sam", "1990-02-15", "709-555-0202");
        Medication medication = new Medication("Amoxicillin", "500mg", 20, LocalDate.now().plusDays(30));
        String instruction = "twice daily";

        DatabaseMedicationService medicationService = new DatabaseMedicationService();
        DatabasePatientService patientService = new DatabasePatientService();
        DatabaseDoctorService doctorService = new DatabaseDoctorService();

        List<Medication> medications = medicationService.getAllMedications();
        List<Patient> patients = patientService.getAllPatients();
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<Prescription> prescriptions = medicationService.getAllPrescriptions();


        Prescription newPrescription = medicationService.createPrescription(doctor, patient, medication, instruction);
        List<Medication> expiredMeds = medicationService.getExpiredMedications();

        // Displaying prescriptions by a doctor
        List<Prescription> prescriptionsByDoctor = medicationService.getPrescriptionsByDoctor(doctor);
        System.out.println("Prescriptions by Doctor: " + prescriptionsByDoctor);
        System.out.println("Expired Meds: " + expiredMeds);


//        service.addPatient(newPatient);


//        // - formatList
//        public <T> String formatList(List<T> list) {
//            return list.stream()
//                    .map(Object::toString)
//                    .collect(Collectors.joining("\n"));
//        }
//        // - Generate a report of all system data
//        public String generateFullReport() {
//            return """
//        Patients:
//        %s
//
//        Medications:
//        %s
//
//        Doctors:
//        %s
//        """.formatted(formatList(patients), formatList(medications), formatList(doctors));
//        }
    }
}

