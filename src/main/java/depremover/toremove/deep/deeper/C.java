package depremover.toremove.deep.deeper;

import java.lang.annotation.Native;

public class C {

    @Deprecated
    @Native
    protected int field = 10;

    /**
     * To be removed.
     */
    @Native
    @Deprecated
    protected int field2 = 100;

    protected int field3 = 1000;

    public static void main(String[] args) {

    }
}
