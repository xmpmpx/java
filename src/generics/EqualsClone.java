package generics;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;


class EqualsClone<K, V> {
    private K klucz;
    private V wartosc;

    public EqualsClone(K klucz, V wartosc) {
        this.klucz = klucz;
        this.wartosc = wartosc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EqualsClone<?, ?> para = (EqualsClone<?, ?>) o; // nie wiadomo czy te same typy - może być też typ goły, choć niezalecane
        return Objects.equals(klucz, para.klucz) &&
                Objects.equals(wartosc, para.wartosc);
    }

    @Override
    protected EqualsClone<K, V> clone() throws CloneNotSupportedException {
        EqualsClone<K, V> obj = (EqualsClone<K, V>) super.clone();

        // nie można utworzyć obiektu nieznanego typu
        // należy użyć refleksji
        try {
            Class<?> klasa = this.klucz.getClass();
            Method metoda = klasa.getMethod("clone");
            Object klon = metoda.invoke(this.klucz);
            obj.klucz = (K) klon;

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public int hashCode() {
        return Objects.hash(klucz, wartosc);
    }
}
