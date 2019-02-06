package generics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class RefleksjaGet<E> {

    public static List<String> lista = new ArrayList<>();
    public static List lista2 = new ArrayList<String>();

    public E pole;

    public <K, V> E generyczna() {
        return pole;
    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        // informacje statyczne

        Field pole = RefleksjaGet.class.getDeclaredField("lista");
        Type genericType = pole.getGenericType();

        System.out.println(pole);
        System.out.println(genericType);
        System.out.println(genericType instanceof ParameterizedType);

        pole = RefleksjaGet.class.getDeclaredField("lista2");
        genericType = pole.getGenericType();

        System.out.println(pole);
        System.out.println(genericType);
        System.out.println(genericType instanceof ParameterizedType);

        // informacje dynamiczne

        Class<?> classLista = lista.getClass();
        Class<?> classLista2 = lista2.getClass();

        System.out.println(classLista);
        System.out.println(classLista2);

        TypeVariable<? extends Class<?>>[] typeParametersForLista = classLista.getTypeParameters();
        TypeVariable<? extends Class<?>>[] typeParametersForLista2 = classLista2.getTypeParameters();

        System.out.println(typeParametersForLista[0]);
        System.out.println(typeParametersForLista2[0]);

        // Metody

        Method metoda = RefleksjaGet.class.getDeclaredMethod("generyczna");
        Type genericReturnType = metoda.getGenericReturnType();

        System.out.println("Metody");
        System.out.println(genericReturnType);
        System.out.println(metoda.toGenericString());
    }

}

