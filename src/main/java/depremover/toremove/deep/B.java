package depremover.toremove.deep;

import java.lang.annotation.Native;

public class B {

    @Native
    protected int field3 = 100;

    @Override
    @Deprecated
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Native
    @Deprecated
    protected int field2 = 100;

    /**
     * To be removed.
     */
    @Deprecated
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void methodVoid() {
        System.out.println("Void");
    }


    public static void main(String[] args) {

    }
}
