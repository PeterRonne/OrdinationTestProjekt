package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    Laegemiddel laegemiddel;
    DagligFast dagligFast;
    @BeforeEach
    void setUp() {
        laegemiddel = new Laegemiddel("Acetylsalicylsyre",0.1,0.15,0.16,"Styk");
        dagligFast = new DagligFast(LocalDate.of(2024,03,18), LocalDate.of(2024,03,28),laegemiddel);
        dagligFast.opretDosis(0.25,0.5,0.5,0.25);

    }

    @Test
    void DagligFast() {
        // Arrange
        LocalDate expectedStart;
        LocalDate expectedSlut;
        LocalDate actualStart;
        LocalDate actualSlut;
        Laegemiddel expectedLaegemiddel;
        Laegemiddel actualLaegemiddel;

        // Act
        expectedStart = LocalDate.of(2024,03,18);
        expectedSlut = LocalDate.of(2024,03,28);
        actualStart = dagligFast.getStartDen();
        actualSlut = dagligFast.getSlutDen();
        expectedLaegemiddel = laegemiddel;
        actualLaegemiddel = dagligFast.getLaegemiddel();

        // Assert
        assertNotNull(dagligFast);
        assertEquals(expectedStart,actualStart);
        assertEquals(expectedSlut,actualSlut);
        assertEquals(expectedLaegemiddel,actualLaegemiddel);

    }


    @Test
    void samletDosis() {
        // Arrange
        double actual;
        double expected;

        // Act
        actual = dagligFast.samletDosis();
        expected = 16.5;

        // Assert
        assertEquals(expected,actual);

    }

    @Test
    void doegnDosis() {
        // Arrange
        double actual;
        double expected;

        // Act
        actual = dagligFast.doegnDosis();
        expected = 1.5;

        // Assert
        assertEquals(expected,actual);
    }

    @Test
    void antalDage() {
        // Arrange
        int actual;
        int expected;

        // Act
        actual = dagligFast.antalDage();
        expected = 11;

        // Assert
        assertEquals(expected,actual);
    }

    @Test
    void opretDosis() {
        // Arrange
        // Done in setup

        // Act
        Dosis[] actual = dagligFast.getDoser();
        Dosis[] expected = new Dosis[4];
        expected[0] = new Dosis(LocalTime.of(7,00,00), 0.25);
        expected[1] = new Dosis(LocalTime.of(12,00,00), 0.5);
        expected[2] = new Dosis(LocalTime.of(18,00,00), 0.5);
        expected[3] = new Dosis(LocalTime.of(22,00,00), 0.25);

        // Assert
        assertArrayEquals(expected,actual);
    }
}