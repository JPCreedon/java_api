package com.infinigongroup.api;

import java.util.Date;

import org.json.simple.JSONObject;

public class Timepoint {
	final public int tweets;
	final public float sentiment;
	final public Date date;

	public Timepoint(int tweets, float sentiment, Date date) {
		this.tweets = tweets;
		this.sentiment = sentiment;
		this.date = date;
	}

	public static Timepoint fromJson(JSONObject jsonTimepoint) {
		return new Timepoint((int) jsonTimepoint.get("tweets"), (float) jsonTimepoint.get("sentiment"),
				(Date) jsonTimepoint.get("date"));
	}
}
