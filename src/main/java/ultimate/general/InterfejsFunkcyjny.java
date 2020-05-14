package ultimate.general;

@FunctionalInterface
public interface InterfejsFunkcyjny {
    int jednaMetoda();

    default int drugaMetoda() {
        return 1;
    }

    static String trzeciaMetoda() {
        return "void";
    }

    String toString();

    boolean equals(Object obj);
}
