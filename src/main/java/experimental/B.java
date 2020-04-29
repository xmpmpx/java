package experimental;

enum KODY {
    PUBLISHED("70J"),
    PRIVATE("758"),
    BOTH("");

    private String padis;

    KODY(String padis) {
        this.padis = padis;
    }

    public static String getPadisFromOTA(String ota) {
        for (KODY kod : values()) {
            if (kod.name().equalsIgnoreCase(ota)) {
                return kod.padis;
            }
        }
        return "";
    }

    public static String getOTAfromPadis(String padis) {
        for (KODY kod : values()) {
            if (kod.padis.equalsIgnoreCase(padis)) {
                return kod.name().substring(0, 1) + kod.name().substring(1).toLowerCase();
            }
        }
        return "";
    }
}

public class B extends A {
    int zmienna;

    public B(int zmienna) {
        this.zmienna = zmienna;
    }

    protected void metoda() {
        System.out.println(KODY.getOTAfromPadis("758"));
        System.out.println(KODY.getOTAfromPadis("70J"));
        System.out.println(KODY.getPadisFromOTA("Published"));
        System.out.println(KODY.getPadisFromOTA("Private"));
        System.out.println(KODY.getPadisFromOTA("Both"));
    }
    protected void only(){
        System.out.println("Only!");
    }
}

class Run {
    public static void main(String[] args) {
        B b = new B(1);
        b.metoda();
        koko(b);

        A a = new B(1);
        C spoko = spoko((B) a);
        koko(a);

        try {
            error();
        } catch (SuperError e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getLocalizedMessage());
        }
    }

    public static void koko(A obj) {
        System.out.println(obj);
        ((B)obj).only();
    }

    public static C spoko(B loko) {
        return loko;
    }

    @Anno(value = KODY.PRIVATE, val = {"a", "b"})
    public static void error() throws SuperError {
        //throw new SuperError("Error!");
    }
}