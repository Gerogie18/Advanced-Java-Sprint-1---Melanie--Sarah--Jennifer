package main.service;
import main.model.Patient;
import main.model.Prescription;
import java.util.List;

public interface PatientService {
    void addPatient(Patient patient);
    void removePatient(int patientId);
    List<Patient> searchPatientsById(int patientId);
    List<Patient> searchPatientsByFirstName(String firstName);
    List<Patient> searchPatientsByLastName(String lastName);
    List<Prescription> getPrescriptionsByPatient(Patient patient);
    List<Prescription> getPrescriptionsByPatientId(int patientId);
}
