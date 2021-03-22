package pl.zzpj2021.solid.dip.weathertracker.solution;


import pl.zzpj2021.solid.dip.weathertracker.violation.Emailer;
import pl.zzpj2021.solid.dip.weathertracker.violation.Phone;

public class WeatherTracker {
    String currentConditions;
    Device device;

    public WeatherTracker(Device device) {
        this.device = device;
    }

    public void setCurrentConditions(String weatherDescription) {
        this.currentConditions = weatherDescription;
        if (weatherDescription.equals("rainy")) {
            String alert = device.generateWeatherAlert(weatherDescription);
            System.out.print(alert);
        }
        if (weatherDescription.equals("sunny")) {
            String alert = device.generateWeatherAlert(weatherDescription);
            System.out.print(alert);
        }
    }
}
