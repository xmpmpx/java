package generics;

public class Exceptions<E> { // nie można extends Exception, gdyż można byłoby łapać go wielokrotnie
    // z różnym parametrem typu, a po wymazaniu byłby to ten sam wyjątek łapany wieloktrotnie co być nie może

    public <E extends Exception> void metoda() {
        //try {

        // } catch (E e) { // cannot catch type parameters - podobnie bo będzie zamieniony na Exception jako skrajne lewe
        // ograniczenie typu

        // }
    }

    public <E extends Exception> void metoda2() throws E { // jedyna sytuacja kiedy może być
        throw new NullPointerException();
    }

}
