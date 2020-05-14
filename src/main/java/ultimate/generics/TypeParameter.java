package ultimate.generics;

public class TypeParameter {

    // METODY
    // Parametr typu dla metod może występować wszędzie.
    // Zarówno dla metody statycznej jak i niestatycznej
    // Paramter typu dla metody obejmuje wszystko od jej początku do końca

    // KLASY
    // Tylko w kontekście niestatycznym, nawet w inicjalizatorze niestatycznym
    // NIE MOŻĘ kontekście statycznym (w typie enum też nie, ani w zagnieżdżonym interfejsie klasy,
    // gdyż są one niejawnie statyczne)
}
