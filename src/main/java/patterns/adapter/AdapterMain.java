package patterns.adapter;

import patterns.adapter.newapi.APINewImpl;
import patterns.adapter.oldapi.APIOldImpl;

public class AdapterMain {
    public static void main(String[] args) {

        APIUsage oldAPIusage = new APIUsage(new APIOldImpl());
        APIUsage newAPIusage = new APIUsage(new APINewToAPIOldAdapter(new APINewImpl()));
    }
}
