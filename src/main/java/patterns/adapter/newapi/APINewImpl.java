package patterns.adapter.newapi;

public class APINewImpl implements APINew {

    @Override
    public int createNew() {
        return 1;
    }

    @Override
    public void removeNew() {
        System.out.println("New remove");
    }

    @Override
    public void updateNew() {
        System.out.println("New update");
    }
}
