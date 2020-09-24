package patterns.observer;

public interface Observable {

    void registerObservers(Observer o);

    void unregisterObservers(Observer o);

    void notifyObservers();
}
