package ordination;

import jdk.jfr.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    private DagligSkaev dagligSkæv;
    private Laegemiddel acetylsalicylsyre;

    @BeforeEach
    void setUp() {
        LocalDate startDen = LocalDate.of(2024, 03, 10);
        LocalDate slutDen = LocalDate.of(2024, 03, 15);
        acetylsalicylsyre = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");

        dagligSkæv = new DagligSkaev(startDen, slutDen, acetylsalicylsyre);

        dagligSkæv.opretDosis(LocalTime.of(7, 0), 2);
        dagligSkæv.opretDosis(LocalTime.of(12, 0), 3);
        dagligSkæv.opretDosis(LocalTime.of(16, 0), 1);
        dagligSkæv.opretDosis(LocalTime.of(16, 0), 2);
        dagligSkæv.opretDosis(LocalTime.of(16, 0), 1);


    }

    @Test
    void constructorTest() {
        //Arrange
        LocalDate expectedStartDate = LocalDate.of(2024, 03, 10);
        LocalDate expectedEndDate = LocalDate.of(2024, 03, 15);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        String expectedLeagemiddel = laegemiddel.getNavn();
        //Act
        LocalDate actualStartDate = dagligSkæv.getStartDen();
        LocalDate actualEndDate = dagligSkæv.getSlutDen();
        String actualLaegemiddel = dagligSkæv.getLaegemiddel().getNavn();

        //Assert
        assertEquals(expectedStartDate, actualStartDate);
        assertEquals(expectedEndDate, actualEndDate);
        assertEquals(expectedLeagemiddel, actualLaegemiddel);
        assertNotNull(dagligSkæv);
    }

    @Test
    void opretDosis() {
        //Arrange
        int expectedSize = dagligSkæv.getDoser().size() + 1;
        LocalTime expectedTime = LocalTime.of(16, 0);
        double expectedAntal = 0.2;
        //Act
        Dosis dosis = dagligSkæv.opretDosis(LocalTime.of(16, 0), 0.2);
        int actualSize = dagligSkæv.getDoser().size();
        LocalTime actualTime = dosis.getTid();
        double actualAntal = dosis.getAntal();

        //Assert
        assertNotNull(dosis);
        assertEquals(expectedSize, actualSize);
        assertEquals(expectedTime, actualTime);
        assertEquals(expectedAntal, actualAntal);
    }

    @Test
    @Name("TC1: SamletDosis() for en liste med flere elementer, hvor start og slut er ens")
    void TC1samletDosisTest() {
        LocalDate startDen = LocalDate.of(2024, 03, 10);
        DagligSkaev dagligSkaev = new DagligSkaev(startDen, startDen, acetylsalicylsyre);
        dagligSkaev.opretDosis(LocalTime.of(7, 0), 2);
        dagligSkaev.opretDosis(LocalTime.of(12, 0), 3);
        dagligSkaev.opretDosis(LocalTime.of(16, 0), 1);
        dagligSkaev.opretDosis(LocalTime.of(16, 0), 2);
        dagligSkaev.opretDosis(LocalTime.of(16, 0), 1);

        //Arrange
        double expected = 9;

        //Act
        double actual = dagligSkaev.samletDosis();

        //Assert
        assertEquals(expected, actual, 0.001);
    }

    @Test
    @Name("TC2: SamletDosis() for en liste med flere elementer, hvor start og slut IKKE er ens")
    void TC2samletDosisTest() {
        //Arrange
        double expected = 54;

        //Act
        double actual = dagligSkæv.samletDosis();

        //Assert
        assertEquals(expected, actual, 0.001);
    }

    @Test
    @Name("TC3: SamletDosis() for en liste med et element, hvor start og slut er ens")
    void TC3samletDosisTest() {
        LocalDate startDen = LocalDate.of(2024, 03, 10);
        DagligSkaev dagligSkaev = new DagligSkaev(startDen, startDen, acetylsalicylsyre);
        dagligSkaev.opretDosis(LocalTime.of(7,0),5);
        //Arrange
        double expected = 5;

        //Act
        double actual = dagligSkaev.samletDosis();

        //Assert
        assertEquals(expected, actual, 0.001);
    }

    @Test
    @Name("TC4: SamletDosis() for en liste med et element, hvor start og slut IKKE er ens")
    void TC4samletDosisTest() {
        LocalDate startDen = LocalDate.of(2024, 03, 10);
        LocalDate slutDen = LocalDate.of(2024, 03, 15);

        DagligSkaev dagligSkaev = new DagligSkaev(startDen, slutDen, acetylsalicylsyre);
        dagligSkaev.opretDosis(LocalTime.of(7,0),5);
        //Arrange
        double expected = 30;

        //Act
        double actual = dagligSkaev.samletDosis();

        //Assert
        assertEquals(expected, actual, 0.001);
    }

    @Test
    @Name("TC1 doegnDosis() for en liste med flere elementer")
    void TC1doegnDosis() {
        //Arrange
        int expected = 9;

        //Act
        double actual = dagligSkæv.doegnDosis();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @Name("TC 2doegnDosis() for en liste med et element")
    void TC2doegnDosis() {
        LocalDate startDen = LocalDate.of(2024, 03, 10);
        LocalDate slutDen = LocalDate.of(2024, 03, 15);

        DagligSkaev dagligSkaev = new DagligSkaev(startDen, slutDen, acetylsalicylsyre);
        dagligSkaev.opretDosis(LocalTime.of(7,0),5);
        //Arrange
        int expected = 5;

        //Act
        double actual = dagligSkaev.doegnDosis();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @Name("TC1 antalDage hvor start og slut datoer er forskellige")
    void TC1antalDage() {
        //Arrange
        int expected = 6;

        //Act
        int actual = dagligSkæv.antalDage();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    @Name("TC2 antalDage hvor start og slut datoer er forskellige")
    void TC2antalDage() {
        //Arrange
        LocalDate startDen = LocalDate.of(2024, 03, 10);
        DagligSkaev dagligSkaev = new DagligSkaev(startDen, startDen, acetylsalicylsyre);
        int expected = 1;

        //Act
        int actual = dagligSkaev.antalDage();

        //Assert
        assertEquals(expected, actual);
    }


}