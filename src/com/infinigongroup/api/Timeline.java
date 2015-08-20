package com.infinigongroup.api;

import java.net.Proxy;

public class Timeline extends TimeSeriesBase {

	public Timeline(String stream, String resolution) {
		this(stream, resolution, null);
	}

	public Timeline(String stream, String resolution, Proxy proxy) {
		super(stream, resolution, proxy);
	}
	
	@Override
	String getPath() {
		return "/api/timeline";
	}

}
