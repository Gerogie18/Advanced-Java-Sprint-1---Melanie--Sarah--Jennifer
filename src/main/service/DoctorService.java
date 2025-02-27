package main.service;

import main.model.Doctor;
import main.model.Patient;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    void addDoctor(Doctor doctor);

    void removeDoctor(Doctor doctor);

    void removeDoctorById(int doctorId);

    void addPatientToDoctor(Patient patient, Doctor doctor);

    List<Doctor> getAllDoctors();

    List<Doctor> searchDoctorsById(int doctorId);

    List<Doctor> searchDoctorsByFirstName(String firstName);

    List<Doctor> searchDoctorsByLastName(String lastName);

    List<String> getFilterdList(String firstName, String lastName);

    List<Doctor> searchDoctorsBySpeciality(String specialization);

    Optional<Doctor> getDoctorById(int id);
}
