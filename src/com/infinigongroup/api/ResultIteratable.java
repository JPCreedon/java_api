package com.infinigongroup.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public abstract class ResultIteratable implements Iterable<Object> {

	public final String host = "http://realtime.infinigongroup.com";

	Object response = null;
	Object results = null;

	boolean loaded = false;
	int head = 0;
	int total = 0;
	Proxy proxy;
	public String resolution;

	public ResultIteratable() {
		this(null);
	}

	public ResultIteratable(Proxy proxy) {
		this.proxy = proxy;
	}

	public void load() {
		String url = getUrl();
		load(url);
	}

	abstract String getUrl();

	abstract Object getResults();

	public void load(String url_string) {

		HttpURLConnection connection;
		try {
			connection = ((HttpURLConnection) new URL(url_string).openConnection(proxy));
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

	@Override
	public Iterator<Object> iterator() {
		return new Iterator<Object>() {

			@Override
			public boolean hasNext() {
				if (loaded == false)
					load();
				return (head < resultsSize() || responseGet("next") != null);
			}

			@Override
			public Object next() {
				// TODO Auto-generated method stub
				Object jsonTimepoint = getNext();
				if (jsonTimepoint == null) {
					load((String) responseGet("next"));
					jsonTimepoint = getNext();
				}
				return jsonTimepoint;
			}

			private Object getNext() {
				if (head < resultsSize()) {
					Object jsonTimepoint = resultsGet(head);
					head++;
					return jsonTimepoint;
				}
				return null;
			}

		};
	}

}
