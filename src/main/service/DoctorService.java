package main.service;
import main.model.Doctor;
import main.model.Patient;

import java.util.List;

public interface DoctorService {
        void addDoctor(Doctor doctor);
        void removeDoctor(Doctor doctor);
        void removeDoctorById(int doctorId);
        void addPatientToDoctor(Patient patient, Doctor doctor);
        List<Doctor> searchDoctorsById(int doctorId);
        List<Doctor> searchDoctorsByFirstName(String firstName);
        List<Doctor> searchDoctorsByLastName(String lastName);
        List<Doctor> searchDoctorsBySpeciality(String specialization);
        //List<Prescription> getPrescriptionsByDoctorId(int doctorId);

    }


