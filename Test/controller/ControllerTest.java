package controller;

import jdk.jfr.Name;
import ordination.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void opretPNOrdination() {
        Controller controller = Controller.getTestController();
        Patient patient = controller.opretPatient("123456-7890","Jens Jensen",60);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Acetylsalicylsyre",0.1,0.15,0.16,"Styk");
        LocalDate start = LocalDate.of(2024,03,18);
        LocalDate slut  = LocalDate.of(2024,03,28);
        int antalEnheder = 2;

        // Act
        PN pn = controller.opretPNOrdination(start,slut,patient,laegemiddel,antalEnheder);
        LocalDate actualStart = pn.getStartDen();
        LocalDate actualSlut = pn.getSlutDen();

        // Assert
        assertNotNull(pn);
        assertNotNull(pn.getLaegemiddel());
        assertTrue(patient.getOrdinationer().contains(pn));
        assertEquals(start,actualStart);
        assertEquals(slut,actualSlut);
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
    @Name("TC1 anbefaletDosisPrDoegn() vægt = 24 ")
    void anbefaletDosisPrDoegn() {
        //Arrange
        Controller controller = Controller.getController();
        Patient patient = controller.opretPatient("123456-7890","Ole Olesen",24);
        Laegemiddel leagemiddel = controller.opretLaegemiddel("Acetylsalicylsyre", 0.1,0.15,0.16, "styk");

        double expected = 2.4;
        //Act
        double actual = controller.anbefaletDosisPrDoegn(patient, leagemiddel);

        //Assert
        assertEquals(expected,actual, 0.001);
    }

    @Test
    @Name("TC2 anbefaletDosisPrDoegn() vægt = 25 ")
    void TC2anbefaletDosisPrDoegn() {
        Controller controller = Controller.getController();
        Patient patient = controller.opretPatient("123456-7890","Ole Olesen",25);
        Laegemiddel leagemiddel = controller.opretLaegemiddel("Acetylsalicylsyre", 0.1,0.15,0.16, "styk");

        double expected = 3.75;

        double actual = controller.anbefaletDosisPrDoegn(patient, leagemiddel);

        //Assert
        assertEquals(expected,actual, 0.001);
    }

}