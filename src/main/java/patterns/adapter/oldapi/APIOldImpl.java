package patterns.adapter.oldapi;

public class APIOldImpl implements APIOld{

    @Override
    public int create() {
        return 0;
    }

    @Override
    public void remove() {
        System.out.println("Old remove");
    }

    @Override
    public void update() {
        System.out.println("Old update");
    }
}
