package patterns.adapter;

import patterns.adapter.newapi.APINew;
import patterns.adapter.oldapi.APIOld;

public class APINewToAPIOldAdapter implements APIOld {

    private APINew apiNew;

    public APINewToAPIOldAdapter(APINew apiNew) {
        this.apiNew = apiNew;
    }

    @Override
    public int create() {
        return apiNew.createNew();
    }

    @Override
    public void remove() {
        apiNew.removeNew();
    }

    @Override
    public void update() {
        apiNew.updateNew();
    }
}
