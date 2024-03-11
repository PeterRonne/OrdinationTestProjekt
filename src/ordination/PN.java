package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PN extends Ordination {

    private final List<LocalDate> ordinationer = new ArrayList<>();
    private double antalEnheder;

    public PN(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, double antalEnheder) {
        super(startDen, slutDen, laegemiddel);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     *
     * @param givesDen
     * @return
     */

    public boolean givDosis(LocalDate givesDen) {
        if (givesDen.isAfter(super.getStartDen()) && givesDen.isBefore(super.getSlutDen())) {
            ordinationer.add(givesDen);
            return true;
        } else {
            return false;
        }
    }

    public double doegnDosis() {
        int dageMellem = (int) ChronoUnit.DAYS.between(super.getStartDen() ,super.getSlutDen());
        double doegnDosis = (getAntalGangeGivet() * antalEnheder) / dageMellem;
        return doegnDosis;
    }

    @Override
    public String getType() {
        // TODO
        return null;
    }

    public double samletDosis() {
        return getAntalGangeGivet() * antalEnheder;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     *
     * @return
     */
    public int getAntalGangeGivet() {
        return ordinationer.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
