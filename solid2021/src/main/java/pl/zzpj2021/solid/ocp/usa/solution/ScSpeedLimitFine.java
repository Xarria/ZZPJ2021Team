package pl.zzpj2021.solid.ocp.usa.solution;

public class ScSpeedLimitFine implements SpeedLimitFine {

    private static final int SC_MAX_SPEED = 30;
    private static final int SC_FINE = 100;

    @Override
    public double calculateSpeedLimitFine(int speed) {
        if (speed > SC_MAX_SPEED) {
            return SC_FINE;
        }
        return 0;
    }
}
