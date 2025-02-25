import main.service.*;
import main.model.Patient;
import main.model.Person;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class DatabasePatientServiceTest {
    private DatabasePatientService service;
    private Patient patient;

    @BeforeEach
    void setUp() {
        // Initialize the service
        service = new DatabasePatientService();

        // Create new patient instances
        patient = new Patient("Wassisname", "Buddy", "1981-01-01", "7095551234");
        service.addPatient(patient);
    }

    @Test
    void addPatient() {
        Patient newPatient = new Patient("Fellas", "Other", "1981-01-02", "7095555678");
        service.addPatient(newPatient);
        // Use the searchPatientsByLastName to retrieve patients
        List<Patient> foundPatients = service.searchPatientsByLastName("Fellas");
        // Assert that the list of found patients contains the added patient
        assertTrue(foundPatients.contains(newPatient), "The patient should be found by their last name after being added.");
    }

    @Test
    void removePatient() {
        service.removePatient(patient);
        List<Patient> allPatients = service.getAllPatients();
        assertFalse(allPatients.contains(patient));
    }

    @Test
    void testRemovePatient() {
    }


    @Test
    void searchPatientsByFirstName() {
        List<Patient> foundPatients = service.searchPatientsByFirstName("Buddy");
        // Assert that the list of found patients contains the added patient
        assertTrue(foundPatients.contains(patient), "The patient should be found by their first name after being added.");
    }

    @Test
    void searchPatientsByLastName() {
        List<Patient> foundPatients = service.searchPatientsByLastName("Wassisname");
        // Assert that the list of found patients contains the added patient
        assertTrue(foundPatients.contains(patient), "The patient should be found by their last name after being added.");
    }

    @Test
    void getPrescriptionsByPatient() {
    }
}