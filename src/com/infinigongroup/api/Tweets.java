package com.infinigongroup.api;

import java.net.Proxy;

public class Tweets extends ResultIteratable {
	public final String path = "/api/tweets/";
	public final String stream;
	public final String resolution;

	public Tweets(String stream, String resolution) {
		super(null);
		this.stream = stream;
		this.resolution = resolution;
	}

	public Tweets(String stream, String resolution, Proxy proxy) {
		super(proxy);
		this.stream = stream;
		this.resolution = resolution;

	}

	public String getUrl() {
		return host + path + "/" + stream + "/" + resolution + "/?format=json";
	}

	@Override
	Object getResults() {
		return responseGet("results");
	}

}
