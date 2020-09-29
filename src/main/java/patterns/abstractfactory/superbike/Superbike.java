package patterns.abstractfactory.superbike;

public abstract class Superbike {
    String kolor;
    int moc;

    public Superbike(String kolor, int moc) {
        this.kolor = kolor;
        this.moc = moc;
    }

    @Override
    public String toString() {
        return "Superbike{" +
                "kolor='" + kolor + '\'' +
                ", moc=" + moc +
                '}';
    }
}
