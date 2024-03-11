package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligFast extends Ordination{
    private Dosis[] doser = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
        super(startDen, slutDen, laegemiddel);
    }

    public void opretDosis (double morgen, double middag, double aften, double nat) {
        doser[0] = new Dosis(LocalTime.of(7,00,00), morgen);
        doser[1] = new Dosis(LocalTime.of(12,00,00), middag);
        doser[2] = new Dosis(LocalTime.of(18,00,00), aften);
        doser[3] = new Dosis(LocalTime.of(22,00,00), nat);
    }

    public Dosis[] getDoser() {
        Dosis[] doserCopy = new Dosis[4];
        doserCopy = this.doser;
        return doserCopy;
    }

    @Override
    public double samletDosis() {
        return doegnDosis() * super.antalDage();
    }

    @Override
    public double doegnDosis() {
        double samletDosis = 0;
        for (Dosis dosis : doser) {
            samletDosis += dosis.getAntal();
        }
        return samletDosis;
    }

    @Override
    public String getType() {
        return "DagligFast";
    }
}
