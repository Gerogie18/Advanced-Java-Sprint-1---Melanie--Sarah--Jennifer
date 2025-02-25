import main.service.*;
import main.model.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;

class DatabaseMedicationServiceTest {
    private DatabaseMedicationService service;
    private Medication medication;
    private Prescription prescription;


    @BeforeEach
    void setUp() {
        service = new DatabaseMedicationService();
        medication = new Medication("Aspirin", "100mg", 100, LocalDate.now().plusDays(30));
        service.addMedication(medication);
        prescription = service.createPrescription(new Doctor("Doe", "Jo", "1980-01-01", "709-555-1234", "Cariology"), new Patient("Best", "Terry", "1980-01-01", "709-555-1234"), medication, "Take one daily");
    }

    @Test
    void getAllMedications() {
        assertEquals(1, service.getAllMedications().size());
    }

    @Test
    void addMedication() {
        Medication newMed = new Medication("Ibuprofen", "200mg", 50, LocalDate.now().plusDays(60));
        service.addMedication(newMed);
        assertTrue(service.getAllMedications().contains(newMed));
    }

    @Test
    void removeMedicationById() {
        service.removeMedicationById(medication.getId());
        assertTrue(service.getAllMedications().isEmpty());
    }

    @Test
    void updateMedication() {
        String newName = "Aspirin Plus";
        String newDose = "150mg";
        service.updateMedication(medication, newName, newDose);
        assertEquals(newName, medication.getName());
        assertEquals(newDose, medication.getDose());
    }

    @Test
    void getMedicationById() {
        assertEquals(medication, service.getMedicationById(medication.getId()).orElse(null));
    }

    @Test
    void adjustMedicationInventory() {
        int adjustment = 50;
        service.adjustMedicationInventory(medication, adjustment);
        assertEquals(150, medication.getStockQuantity());
    }

    @Test
    void getAllPrescriptions() {
        assertEquals(1, service.getAllPrescriptions().size());
    }

    @Test
    void getPrescriptionById() {
        assertEquals(prescription, service.getPrescriptionById(prescription.getId()).orElse(null));
    }

    @Test
    void createPrescription() {
        Prescription newPrescription = service.createPrescription(new Doctor("Farr", "Sam", "1980-01-01", "709-555-1234", "GP"), new Patient("Field", "Ashley", "1980-01-01", "709-555-1234"), medication, "Take twice daily");
        assertNotNull(newPrescription);
        assertTrue(service.getAllPrescriptions().contains(newPrescription));
    }

    @Test
    void updatePrescription() {
        String newInstructions = "Take one twice daily";
        Prescription updatedPrescription = service.updatePrescription(prescription, "200mg", newInstructions);
        assertEquals("200mg", updatedPrescription.getMedication().getDose());
        assertEquals(newInstructions, updatedPrescription.getInstructions());
    }

    @Test
    void renewPrescription() {
        Prescription renewedPrescription = service.renewPrescription(prescription);
        assertNotNull(renewedPrescription, "New prescription should be added");
        assertNotEquals(prescription.getId(), renewedPrescription.getId(), "New prescription should not have the same id as old prescription");
        assertEquals(prescription.getMedication().getName(), renewedPrescription.getMedication().getName(), "New prescription should have the same medications");
    }

    @Test
    void getPrescriptionsByDoctor() {
        Doctor doctor = prescription.getDoctor();
        List<Prescription> foundPrescriptions = service.getPrescriptionsByDoctor(doctor);
        assertTrue(foundPrescriptions.contains(prescription));
    }

    @Test
    void getExpiredMedications() {
        Medication expiredMedication = new Medication("Expired Drug", "50mg", 20, LocalDate.now().minusDays(1));
        service.addMedication(expiredMedication);
        List<Medication> expiredMeds = service.getExpiredMedications();
        assertTrue(expiredMeds.contains(expiredMedication));
    }

}