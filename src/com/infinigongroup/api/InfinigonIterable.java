/**
 *
 */
package com.infinigongroup.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Iterator;

/**
 * @author thvassil
 */
public abstract class InfinigonIterable implements Iterable<Object> {


    public final String host = "http://realtime.infinigongroup.com";

    Object response = null;
    Object results = null;

    boolean loaded = false;
    int head = 0;

    Proxy proxy;

    public InfinigonIterable() {
        this(null);
    }

    public InfinigonIterable(Proxy proxy) {
        this.proxy = proxy;
    }

    public void load() {
        String url = getUrl();
        load(url);
    }

    abstract String getUrl();

    abstract Object getResults();

    protected void load(String url_string) {
        System.out.println(url_string);
        HttpURLConnection connection;


        try {
            if (proxy != null)
                connection = ((HttpURLConnection) new URL(url_string).openConnection(proxy));
            else
                connection = ((HttpURLConnection) new URL(url_string).openConnection());
            if (getToken() != null)
                connection.setRequestProperty("Authorization", "Token " + getToken());
            connection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = parse(br);
            results = getResults();
            head = 0;
            loaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns a JSON object: content of a REST GET The br argument must specify
     * an absolute {@link BufferedReader}.
     * <p>
     * <p>
     * If you need to use a JSON library other than the default, you have to
     * override this method.
     *
     * @param br BufferedReader of the URL GET content result.
     * @return A JSON object representation of the response content.
     * @see BufferedReader
     */

    protected Object parse(BufferedReader br) {

        return JSONValue.parse(br);
    }


    protected int resultsSize() {
        return ((JSONArray) results).size();
    }


    protected Object resultsGet(int index) {
        return ((JSONArray) results).get(index);
    }


    protected Object responseGet(String key) {
        return ((JSONObject) response).get(key);
    }


    private Object getNext() {
        if (head < resultsSize()) {
            Object jsonTimepoint = resultsGet(head);
            head++;
            return jsonTimepoint;
        }
        return null;
    }

    public boolean hasMore() {
        if (loaded == false)
            load();
        return (head < resultsSize() || responseGet("next") != null);
    }

    public Object nextRecord() {
        Object jsonTimepoint = getNext();
        if (jsonTimepoint == null) {
            load((String) responseGet("next"));
            jsonTimepoint = getNext();
        }
        return jsonTimepoint;
    }


    @Override
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            public boolean hasNext() {
                return hasMore();
            }

            public Object next() {
                return nextRecord();
            }


        };
    }


    public String getToken() {
        return System.getenv().get(getEnvTokenKey());

    }

    public String getEnvTokenKey() {
        return "INFINIGON_TOKEN";
    }
}
