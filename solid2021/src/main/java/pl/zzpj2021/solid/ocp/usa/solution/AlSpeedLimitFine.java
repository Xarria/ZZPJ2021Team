package pl.zzpj2021.solid.ocp.usa.solution;

public class AlSpeedLimitFine implements SpeedLimitFine {

    private static final int AL_MAX_SPEED = 34;
    private static final int AL_FINE = 110;

    @Override
    public double calculateSpeedLimitFine(int speed) {
        if (speed > AL_MAX_SPEED) {
            return AL_FINE;
        }
        return 0.0;
    }
}
