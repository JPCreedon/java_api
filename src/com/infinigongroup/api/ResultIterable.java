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


/**
 * An abstract class that lets you iterate through paged json lists of records.
 * 
 * @author      Thanos Vassilakis
 * @version     %I%, %G%
 * @since       1.0
 */
public abstract class ResultIterable implements Iterable<Object> {

	public final String host = "http://api.infinigongroup.com";

	Object response = null;
	Object results = null;

	boolean loaded = false;
	int head = 0;
	int total = 0;
	Proxy proxy;
	
	public ResultIterable() {
		this(null);
	}

	public ResultIterable(Proxy proxy) {
		this.proxy = proxy;
	}

	public void load() {
		String url = getUrl();
		load(url);
	}

	abstract String getUrl();

	abstract Object getResults();

	protected void load(String url_string) {

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

	
	/**
	 * Returns a JSON object: content of a REST GET
	 * The br argument must specify an absolute {@link BufferedReader}. 
	 * <p> 
	 * 
	 * If you need to use a JSON library other than the default, you have to override this method.
	 * 
	 * @param  br  BufferedReader of the URL GET content result.
	 * @return      A JSON object representation of the response content.
	 * @see         BufferedReader
	 */

	protected Object parse(BufferedReader br) {

		return JSONValue.parse(br);
	}

	
	
	/**
	 * Returns the  size of the JOSN array holding a list of results.
	 * <p> 
	 * 
	 * If you need to use a JSON library other than the default, you have to override this method.
	 * 
	 * @return      Size of the JOSN array holding a list of results.
	 * @see         parse
	 */
	protected int resultsSize() {
		return ((JSONArray) results).size();
	}

	/**
	 * Returns the  an array element - a JSON object.
	 * <p> 
	 * 
	 * If you need to use a JSON library other than the default, you have to override this method.
	 * 
	 * @param  index  - the index of the element required.
	 * @return     Returns the  an array element - a JSON object.
	 * @see         parse
	 */
	
	protected Object resultsGet(int index) {
		return ((JSONArray) results).get(index);
	}
	
	
	/**
	 * Returns  a JSON object (possible an array) from the REST JSON response.
	 * <p> 
	 * 
	 * If you need to use a JSON library other than the default, you have to override this method.
	 * 
	 * @param  key  - the key of the object required.
	 * @return     Returns  a JSON object (possible an array) from the REST JSON response.
	 * @see        parse
	 */
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
