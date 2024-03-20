package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PN extends Ordination {

    private final List<LocalDate> ordinationer = new ArrayList<>();
    private double antalEnheder;
    private Laegemiddel laegemiddel;

    public PN(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, double antalEnheder) {
        super(startDen, slutDen, laegemiddel);
        this.antalEnheder = antalEnheder;
        this.laegemiddel = laegemiddel;
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
        if (erIndenForGyldigPeriode(givesDen)) {
            ordinationer.add(givesDen);
            return true;
        } else {
            return false;
        }
    }

    public void tilføjOrdinationsDato(LocalDate dato) {
        ordinationer.add(dato);
    }

    public double doegnDosis() {
        double doegnDosis = 0;
        if (!ordinationer.isEmpty()) {
            int dageMellem = (int) ordinationer.getLast().toEpochDay() - (int) ordinationer.getFirst().toEpochDay();
            doegnDosis = (getAntalGangeGivet() * antalEnheder) / dageMellem;
        }
        return doegnDosis;
    }

    public boolean erIndenForGyldigPeriode(LocalDate givesDen) {
        return givesDen.isAfter(super.getStartDen().minus(1, ChronoUnit.DAYS)) && givesDen.isBefore(super.getSlutDen().plus(1, ChronoUnit.DAYS));
    }

    @Override
    public String getType() {
        return "PN";
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

    public List<LocalDate> getOrdinationer() {
        return new ArrayList<>(ordinationer);
    }

    public Laegemiddel getLaegemiddel() {
        return laegemiddel;
    }

}
