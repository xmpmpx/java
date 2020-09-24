package patterns.observer;

public class TvNews implements Observer {

    @Override
    public void updateForecast(WeatherForecast weatherForecast) {
        System.out.println(
                this.getClass().getSimpleName() + " - Temp: " + weatherForecast.getTemperature()
                        + " - Pressure: " + weatherForecast.getPressure()
        );
    }
}
