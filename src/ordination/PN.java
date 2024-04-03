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
            int dageMellem = (int) senestGivet().toEpochDay() + 1 - (int) tidligstGivet().toEpochDay();
            doegnDosis = (getAntalGangeGivet() * antalEnheder) / dageMellem;
        }
        return doegnDosis;
    }


    public LocalDate tidligstGivet() {
        LocalDate earliestDate = null;
        if (!ordinationer.isEmpty()) {
            earliestDate = ordinationer.get(0);
            for (LocalDate date : ordinationer) {
                if (date.isBefore(earliestDate)) {
                    earliestDate = date;
                }
            }
        }
        return earliestDate;
    }

    public LocalDate senestGivet() {
        LocalDate latestDate = null;
        if (!ordinationer.isEmpty()) {
            latestDate = ordinationer.get(0);
            for (LocalDate date : ordinationer) {
                if (date.isAfter(latestDate)) {
                    latestDate = date;
                }
            }
        }
        return latestDate;
    }

    public boolean erIndenForGyldigPeriode(LocalDate givesDen) {
        if (givesDen == null)
            return false;
        else
            return givesDen.isAfter(super.getStartDen().minusDays(1)) && givesDen.isBefore(super.getSlutDen().plusDays(1));
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
