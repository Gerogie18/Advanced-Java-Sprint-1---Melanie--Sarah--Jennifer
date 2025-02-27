package main.service;

import main.model.Patient;
import main.model.Prescription;
import java.util.List;
import java.util.Optional;

public interface PatientService {
    void addPatient(Patient patient);

    void removePatient(int patientId);

    List<Patient> getAllPatients();

    Optional<Patient> getPatientById(int patientId);

    List<Patient> searchPatientsByFirstName(String firstName);

    List<Patient> searchPatientsByLastName(String lastName);

    List<String> getFilterdList(String firstName, String lastName);

    List<Prescription> getPrescriptionsByPatient(Patient patient);

    List<Prescription> getPrescriptionsByPatientId(int patientId);
}
