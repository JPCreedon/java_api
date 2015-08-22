package com.infinigongroup.api;

import java.net.Proxy;

/**
 * Created by thanos on 8/22/15.
 */
public class Streams extends InfinigonIterable implements Iterable<Object> {


    public Streams() {
        super(null);
    }

    public Streams(Proxy proxy) {
        super(proxy);
    }

    public String getUrl() {
        return host + "/api/tags/?format=json";
    }

    protected Object getResults() {
        return responseGet("results");
    }
}
