package com.infinigongroup.api;

import java.net.Proxy;

public class Timeline extends ResultIteratable {
	public final String path = "/api/timeline/";
	public final String stream;
	public final String resolution;

	public Timeline(String stream, String resolution) {
		super(null);
		this.stream = stream;
		this.resolution = resolution;
	}

	public Timeline(String stream, String resolution, Proxy proxy) {
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
