package com.infinigongroup.api;

import java.util.Date;

public interface TimeSeries extends Iterable<Object>{

	String M = "M";
	String H = "H";
	String d = "d";
	String m = "m";
	String y = "y";

	TimeSeries start(Date startDate);

	TimeSeries stop(Date stoptDate);

	TimeSeries start(String startDate);

	TimeSeries stop(String stopDate);
	
	TimeSeries limit(int limit);

}