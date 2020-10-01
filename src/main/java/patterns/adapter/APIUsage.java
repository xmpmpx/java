package patterns.adapter;

import patterns.adapter.oldapi.APIOld;

public class APIUsage {

    public APIOld apiOld;

    public APIUsage(APIOld apiOld) {
        this.apiOld = apiOld;
        runAPI();
    }

    public void runAPI() {
        System.out.println(apiOld.create());
        apiOld.remove();
        apiOld.update();
    }
}
