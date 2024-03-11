package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination{

    ArrayList<Dosis> doser;

    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
        super(startDen, slutDen, laegemiddel);
        this.doser = new ArrayList<>();
    }

    public ArrayList<Dosis> getDoser() {
        return new ArrayList<>(doser);
    }

    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid,antal);
        doser.add(dosis);
    }

    @Override
    public double samletDosis() {
        double sum = 0;
        for (Dosis dose : doser) {
            sum += dose.getAntal();
        }
        return sum * super.antalDage();
    }

    @Override
    public double doegnDosis() {
        double samletDosis = 0;
        for (Dosis dose : doser) {
            samletDosis += dose.getAntal();
        }
        return samletDosis;
    }


    @Override
    public String getType() {
        return "DagligSkæv";
    }
}
