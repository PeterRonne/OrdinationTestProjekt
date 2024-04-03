package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {

    Patient patient = new Patient("121256-0512", "Jane Jensen", 63.4);
    Laegemiddel lægemiddel = new Laegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
    PN pn = new PN(LocalDate.of(2023, 10, 28), LocalDate.of(2023, 11, 14), lægemiddel, 2);

    @Test
    void PN() {
        //Act
        int expectedOrdniationsLisetLængde = 0;
        int actualOrdniationsLisetLængde = pn.getAntalGangeGivet();
        String expectedLægemiddel = "Paracetamol";
        String actualLægemiddel = pn.getLaegemiddel().getNavn();

        //Assert
        assertNotNull(pn);
        assertEquals(expectedOrdniationsLisetLængde, actualOrdniationsLisetLængde);
        assertEquals(expectedLægemiddel, actualLægemiddel);
    }

    @Test
    void samletDosisTC1() {
        //Arrange & Act
        pn.givDosis(LocalDate.of(2023, 10, 28));
        pn.givDosis(LocalDate.of(2023, 10, 30));

        double expected = 4;
        double actual = pn.samletDosis();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void samletDosisTC2() {
        //Arrange & act
        pn.givDosis(LocalDate.of(2023, 10, 28));
        pn.givDosis(LocalDate.of(2023, 10, 29));
        pn.givDosis(LocalDate.of(2023, 10, 30));
        pn.givDosis(LocalDate.of(2023, 10, 31));

        double expected = 8;
        double actual = pn.samletDosis();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void samletDosisTC3() {
        //Arrange & act
        double expected = 0;
        double actual = pn.samletDosis();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void doegnDosisTC1() {
        //Arrange
        pn.givDosis(LocalDate.of(2023, 11, 1));
        pn.givDosis(LocalDate.of(2023, 11, 1));
        pn.givDosis(LocalDate.of(2023, 11, 2));
        pn.givDosis(LocalDate.of(2023, 11, 2));
        pn.givDosis(LocalDate.of(2023, 11, 3));
        pn.givDosis(LocalDate.of(2023, 11, 3));
        pn.givDosis(LocalDate.of(2023, 11, 4));
        pn.givDosis(LocalDate.of(2023, 11, 4));

        //Act
        double expected = 4;
        double actual = pn.doegnDosis();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void doegnDosisTC2() {
        //Arrange
        pn.givDosis(LocalDate.of(2023, 11, 1));
        pn.givDosis(LocalDate.of(2023, 11, 4));
        pn.givDosis(LocalDate.of(2023, 11, 5));
        pn.givDosis(LocalDate.of(2023, 11, 6));
        pn.givDosis(LocalDate.of(2023, 11, 7));
        pn.givDosis(LocalDate.of(2023, 11, 8));
        pn.givDosis(LocalDate.of(2023, 11, 10));
        pn.givDosis(LocalDate.of(2023, 11, 9));

        //Act
        double expected = 1.6;
        double actual = pn.doegnDosis();

        //Assert
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    void antalDageTC1() {
        //Arrange
        PN pn2 = new PN(LocalDate.of(2023, 3, 10), LocalDate.of(2023, 03, 10), lægemiddel, 2);

        //Act
        int expected = 1;
        int actual = pn2.antalDage();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void antalDageTC2() {
        //Arrange
        PN pn2 = new PN(LocalDate.of(2023, 3, 10), LocalDate.of(2023, 03, 15), lægemiddel, 2);

        //Act
        int expected = 6;
        int actual = pn2.antalDage();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void antalDageTC3() {
        //Arrange
        PN pn2 = new PN(LocalDate.of(2023, 3, 10), LocalDate.of(2023, 03, 18), lægemiddel, 2);

        //Act
        int expected = 9;
        int actual = pn2.antalDage();

        //Assert
        assertEquals(expected, actual);
    }


    @Test
    void getAntalGangeGivetTC1() {
        //Arrange
        pn.givDosis(LocalDate.of(2023, 10, 28));
        pn.givDosis(LocalDate.of(2023, 10, 31));

        //Act
        int expected = 2;
        int actual = pn.getAntalGangeGivet();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getAntalGangeGivetTC2() {
        //Act
        int expected = 0;
        int actual = pn.getAntalGangeGivet();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void givDosisTC1() {
        //Assert
        assertFalse(pn.givDosis(LocalDate.of(2023, 10, 22)));
    }

    @Test
    void givDosisTC2() {
        //Arrange & act
        int expected = pn.getAntalGangeGivet() + 1;
        pn.givDosis(LocalDate.of(2023, 10, 28));
        int actual = pn.getAntalGangeGivet();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void givDosisTC3() {
        //Arrange & act
        int expected = pn.getAntalGangeGivet() + 1;
        pn.givDosis(LocalDate.of(2023, 10, 31));
        int actual = pn.getAntalGangeGivet();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void givDosisTC4() {
        //Arrange & act
        int expected = pn.getAntalGangeGivet() + 1;
        pn.givDosis(LocalDate.of(2023, 11, 5));
        int actual = pn.getAntalGangeGivet();

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void givDosisTC5() {
        //Assert
        assertFalse(pn.givDosis(LocalDate.of(2023, 11, 16)));

    }

}