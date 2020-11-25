package depremover.toremove;

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
}
