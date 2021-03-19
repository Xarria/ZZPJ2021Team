package pl.zzpj2021.solid.ocp.usa.solution;

public class GaSpeedLimitFine implements SpeedLimitFine {

    private static final int GA_MAX_SPEED = 40;
    private static final int GA_FINE = 1400;

    @Override
    public double calculateSpeedLimitFine(int speed) {
        if (speed > GA_MAX_SPEED) {
            return GA_FINE;
        }
        return 0;
    }
}
