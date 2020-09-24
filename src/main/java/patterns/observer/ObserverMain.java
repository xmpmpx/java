package patterns.observer;

public class ObserverMain {

    public static void main(String[] args) {
        WeatherForecast weatherForecast = new WeatherForecast(38, 1001);

        TvNews tvNews = new TvNews();
        RadioNews radioNews = new RadioNews();

        weatherForecast.registerObservers(tvNews);
        weatherForecast.registerObservers(radioNews);
        weatherForecast.notifyObservers();

        System.out.println("----------------------------");

        weatherForecast.setTemperature(42);
        weatherForecast.setPressure(1003);

        weatherForecast.unregisterObservers(tvNews);
        weatherForecast.notifyObservers();
    }
}
