package com.infinigongroup.api;

import java.net.Proxy;

public class Tweets extends TimeSeriesBase {
	public Tweets(String stream, String resolution) {
		this(stream, resolution, null);
	}
	
	public Tweets(String stream, String resolution, Proxy proxy) {
		super(stream, resolution, proxy);
	
	}

	public final String path = "/api/tweets/";


	public String getUrl() {
		return host + path + "/" + stream + "/" + resolution + "/?format=json";
	}


	@Override
	String getPath() {
		return path;
	}
	
}
