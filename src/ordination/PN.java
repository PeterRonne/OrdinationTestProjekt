package ordination;

import java.time.LocalDate;

public class PN extends Ordination {

    private double antalEnheder;
    private String type;

    public PN(double antalEnheder, String type) {
        this.antalEnheder = antalEnheder;
        this.type = type;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */

    public boolean givDosis(LocalDate givesDen) {
        // TODO
        return false;
    }

//(antal gange ordinationen er anvendt * antal enheder) / (antal dage mellem første og sidste givning)
    public double doegnDosis() {
        // TODO
        return 0.0;
    }

    @Override
    public String getType() {
        return null;
    }


    public double samletDosis() {
        // TODO
        return 0.0;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        // TODO
        return-1;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
