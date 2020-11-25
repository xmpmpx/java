package depremover.toremove.deep;

public class B {

    @Override
    @Deprecated
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

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
