package project;

public interface Inter<T> {

    default T display(T obj) {
        return obj;
    }

    static void doSth(){

    }

    static <V> void doSthType(V obj){

    }

    private void doSthElse() {

    }

    void doOneMore();
}
