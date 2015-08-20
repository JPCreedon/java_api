package com.infinigongroup.api;

import java.net.Proxy;

public class Snapshots extends ResultIterable {

	String include_streams;
	String include_tags;
	String include_words;
	String change_3_filter, change_5_filter, change_10_filter;
	String variance_filter;
	String sentiment_filter;
	String include_fields;
	String qs = "";

	public Snapshots() {
		super(null);
	}

	public Snapshots(Proxy proxy) {
		super(proxy);
	}

	public Snapshots streams(String... streams) {
		this.include_streams = String.join(",", streams);
		return this;
	}

	public Snapshots tags(String... tags) {
		this.include_tags = String.join("", tags);
		return this;
	}

	public Snapshots words(String... words) {
		this.include_words = String.join(",", words);
		return this;
	}

	public Snapshots fields(String... fields) {
		this.include_fields = String.join(",", fields);
		return this;
	}

	public Snapshots change_3(int change) {
		this.change_3_filter = "" + change;
		return this;
	}

	public Snapshots change_5(int change) {
		this.change_5_filter = "" + change;
		return this;
	}

	public Snapshots change_10(int change) {
		this.change_10_filter = "" + change;
		return this;
	}

	public Snapshots variance(int variance) {
		this.variance_filter = "" + variance;
		return this;
	}

	public Snapshots sentiment(float sentiment) {
		this.sentiment_filter = "" + sentiment;
		return this;
	}

	@Override
	String getUrl() {
		String url = "http://realtime.infinigongroup.com/api/snapshot/?format=json";
		if (include_streams != null)
			url += "&streams=" + include_streams;
		if (include_tags != null)
			url += "&tags=" + include_tags;
		if (include_words != null)
			url += "&words=" + include_words;
		if (change_3_filter != null)
			url += "&change_3=" + change_3_filter;
		if (change_5_filter != null)
			url += "&change_5=" + change_5_filter;
		if (change_10_filter != null)
			url += "&change_10=" + change_10_filter;
		if (variance_filter != null)
			url += "&variance=" + variance_filter;
		if (sentiment_filter != null)
			url += "&sentiment=" + sentiment_filter;
		if (include_fields != null)
			url += "&fields=" + include_fields;
		return url;
	}

	@Override
	Object getResults() {
		return responseGet("rows");
	}

}
