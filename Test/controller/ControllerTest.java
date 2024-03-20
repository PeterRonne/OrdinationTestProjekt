package controller;

import ordination.DagligFast;
import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void opretPNOrdination() {
    }

    @Test
    void opretDagligFastOrdination() {
        // Arrange
        Controller controller = Controller.getTestController();
        Patient patient = controller.opretPatient("123456-7890","Hans Hansen",60);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Paracetamol",1,1.5,2,"ml");
        LocalDate start = LocalDate.of(2024,03,18);
        LocalDate slut  = LocalDate.of(2024,03,28);
        DagligFast dagligFast;

        // Act
        dagligFast = controller.opretDagligFastOrdination(start,slut,patient,laegemiddel,0.25,0.5,0.5,0.25);
        LocalDate actualStart = dagligFast.getStartDen();
        LocalDate actualSlut = dagligFast.getSlutDen();

        // Assert
        assertNotNull(dagligFast);
        assertNotNull(dagligFast.getLaegemiddel());
        assertTrue(patient.getOrdinationer().contains(dagligFast));
        assertEquals(start,actualStart);
        assertEquals(slut,actualSlut);

    }

    @Test
    void opretDagligSkaevOrdination() {
    }

    @Test
    void ordinationPNAnvendt() {
    }

    @Test
    void anbefaletDosisPrDoegn() {
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }
}