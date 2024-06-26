package ordination;

import java.time.LocalTime;

public class Dosis {
    private LocalTime tid;
    private double antal;

    public Dosis(LocalTime tid, double antal) {
        super();
        this.tid = tid;
        this.antal = antal;
    }

    public double getAntal() {
        return antal;
    }

    public void setAntal(double antal) {
        this.antal = antal;
    }

    public LocalTime getTid() {
        return tid;
    }

    public void setTid(LocalTime tid) {
        this.tid = tid;
    }

    @Override
    public boolean equals(Object other) {
        Dosis castedOther = (Dosis) other;
        boolean same = false;
        double otherAntal = castedOther.getAntal();
        LocalTime otherTime = castedOther.getTid();

        if (tid.equals(otherTime) && antal == otherAntal) {
            same = true;
        }

        return same;
    }
    @Override
    public String toString(){
        return "Kl: " + tid + "   antal:  " + antal;
    }

}
