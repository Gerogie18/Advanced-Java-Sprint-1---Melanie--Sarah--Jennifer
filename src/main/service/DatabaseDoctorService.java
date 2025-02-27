package main.service;

import main.model.Doctor;
import main.model.Patient;
import main.model.Prescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseDoctorService implements DoctorService {
    private List<Doctor> doctors;

    public DatabaseDoctorService() {
        this.doctors = new ArrayList<>();
        ;
    }

    public DatabaseDoctorService(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    public Optional<Doctor> getDoctorById(int id) {
        return doctors.stream()
                .filter(doctor -> doctor.getId() == id)
                .findFirst();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    public void removeDoctorById(int doctorId) {
        doctors.removeIf(doctor -> doctor.getId() == doctorId);
    }

    // Add patients to doctor's list
    public void addPatientToDoctor(Patient patient, Doctor doctor) {
        if (doctor.canAcceptPatients()) {
            doctor.addPatient(patient);
        } else {
            throw new IllegalStateException("Doctor cannot accept more patients.");
        }
    }

    // - Search for doctors by name or ID
    public List<Doctor> searchDoctorsById(int id) {
        return doctors.stream()
                .filter(doctor -> doctor.getId() == id)
                .collect(Collectors.toList());
    }

    public List<Doctor> searchDoctorsByFirstName(String firstName) {
        return doctors.stream()
                .filter(doctor -> doctor.getFirstName().equals(firstName))
                .collect(Collectors.toList());
    }

    public List<Doctor> searchDoctorsByLastName(String lastName) {
        return doctors.stream()
                .filter(doctor -> doctor.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> getFilterdList(String firstName, String lastName) {
        return doctors.stream()
                .filter(Patient -> Patient.getFirstName().equalsIgnoreCase(firstName)
                        || Patient.getLastName().equalsIgnoreCase(lastName))
                .map(doctor -> doctor.toString() + " ID: " + doctor.getId()) // Append ID to the toString result
                .collect(Collectors.toList());
    }

    public List<Doctor> searchDoctorsBySpeciality(String specialization) {
        return doctors.stream()
                .filter(doctor -> doctor.getSpecialization().toLowerCase().contains(specialization.toLowerCase()))
                .collect(Collectors.toList());
    }

}
