import main.service.*;
import main.model.Doctor;
import main.model.Person;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseDoctorServiceTest {
    private DatabaseDoctorService service;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        // Initialize the service
        service = new DatabaseDoctorService();

        // Create new Doctor instances
        doctor = new Doctor("Frakenstein", "Dr", "1980-01-01", "7095555678", "GP");
        service.addDoctor(doctor);
    }

    @Test
    void addDoctor() {
        Doctor newDoctor = new Doctor("Fellas", "Other", "1981-01-02", "7095555678", "Surgeon");
        service.addDoctor(newDoctor);
        // Use the searchDoctorsByLastName to retrieve Doctors
        List<Doctor> foundDoctors = service.searchDoctorsByLastName("Fellas");
        // Assert that the list of found Doctors contains the added Doctor
        assertTrue(foundDoctors.contains(newDoctor), "The Doctor should be found by their last name after being added.");
    }

    @Test
    void removeDoctor() {
        service.removeDoctor(doctor);
        assertFalse(service.searchDoctorsById(doctor.getId()).contains(doctor));
    }

    @Test
    void testRemoveDoctorById() {
    }


    @Test
    void searchDoctorsById() {
        Doctor newDoctor = new Doctor("Fellas", "Other", "1981-01-02", "7095555678", "GP");
        int testID = doctor.getId();
        // Use the searchDoctorsID to retrieve Doctors
        List<Doctor> foundDoctors = service.searchDoctorsById(testID);
        // Assert that the list of found Doctors contains the added Doctor
        assertTrue(foundDoctors.contains(doctor));
        assertFalse(foundDoctors.contains(newDoctor));
    }

    @Test
    void searchDoctorsByFirstName() {
        List<Doctor> foundDoctors = service.searchDoctorsByFirstName("Dr");
        // Assert that the list of found Doctors contains the added Doctor
        assertTrue(foundDoctors.contains(doctor), "The Doctor should be found by their first name after being added.");
    }

    @Test
    void searchDoctorsByLastName() {
        List<Doctor> foundDoctors = service.searchDoctorsByLastName("Frakenstein");
        // Assert that the list of found Doctors contains the added Doctor
        assertTrue(foundDoctors.contains(doctor), "The Doctor should be found by their last name after being added.");
    }

    @Test
    void searchDoctorsBySpeciality() {
        List<Doctor> foundDoctors = service.searchDoctorsBySpeciality("GP");
        Doctor newDoctor = new Doctor("Fellas", "Other", "1981-01-02", "7095555678", "Surgeon");
        // Assert that the list of found Doctors contains the added Doctor
        assertTrue(foundDoctors.contains(doctor), "The Doctor should be found by their speciality.");
        assertFalse(foundDoctors.contains(newDoctor), "The Doctor should be found by the incorrect speciality.");
    }

}