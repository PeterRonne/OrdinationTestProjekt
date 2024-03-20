package controller;

import ordination.DagligFast;
import ordination.DagligSkaev;
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
        // Arrange
        Controller controller = Controller.getTestController();
        Patient patient = controller.opretPatient("123456-7890","Ole Olesen",60);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Paracetamol",1,1.5,2,"ml");
        LocalDate start = LocalDate.of(2024,03,18);
        LocalDate slut  = LocalDate.of(2024,03,28);
        LocalTime[] klokkeslet = {LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(16, 0), LocalTime.of(20, 0), LocalTime.of(0,0)};
        double[] antalEnheder = {0.3,0.3,0.3,0.3,0.3};
        DagligSkaev dagligSkaev;

        // Act
        dagligSkaev = controller.opretDagligSkaevOrdination(start,slut,patient,laegemiddel,klokkeslet,antalEnheder);
        LocalDate actualStart = dagligSkaev.getStartDen();
        LocalDate actualSlut = dagligSkaev.getSlutDen();


        // Assert
        assertNotNull(dagligSkaev);
        assertNotNull(dagligSkaev.getLaegemiddel());
        assertTrue(patient.getOrdinationer().contains(dagligSkaev));
        assertEquals(start,actualStart);
        assertEquals(slut,actualSlut);

        for (int i = 0; i < dagligSkaev.getDoser().size() ; i++) {
            assertEquals(dagligSkaev.getDoser().get(i).getAntal(),0.3);
            assertEquals(dagligSkaev.getDoser().get(i).getTid(),klokkeslet[i]);
        }
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