package patterns.observer;

public class RadioNews implements Observer {

    @Override
    public void updateForecast(WeatherForecast weatherForecast) {
        System.out.println(
                this.getClass().getSimpleName() + " - Temp: " + weatherForecast.getTemperature()
                        + " - Pressure: " + weatherForecast.getPressure()
        );
    }
}
