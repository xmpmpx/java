package depremover.toremove;

import java.lang.annotation.Native;

/**
 * To be removed.
 */
@Deprecated
public class A {

    public int methodInt() {
        return 0;
    }

    @Deprecated
    public String methodString() {
        return "String";
    }

    public void methodVoid() {
        System.out.println("Void");
    }

    @Native
    @Deprecated
    protected int field2 = 100;
}
