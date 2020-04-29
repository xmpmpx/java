package experimental;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class A implements C, D, E {
    int var;

    public A() {
        this.var = var;
         
    }

    protected void metoda(){
        System.out.println(inter());
    }

    public static void metoda(int a){

    }

    public static void main(String[] args) {
        new A().metoda();
    }

    @Override
    public String inter() {
       return C.super.inter();
    }

    public int inter(int a) {
        return a;
    }
}

interface C {
    default String inter(){
        return "C";
    }
}

interface D {
    private String ainter(){
        return "";
    }
}

interface E {
    private String pri(){
        return "";
    }
}

class SuperError extends Exception {
    public SuperError(String message) {
        super(message, new ClassCastException("Rzutowanie sie spitolilo"));
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Anno {
    KODY value();
    String[] val();
}