package com.infinigongroup.api;

import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.net.URLEncoder;
import java.util.Date;

public abstract class TimeSeriesBase extends InfinigonIterable implements TimeSeries {

	
	public final String stream;
	public final String resolution;
	public String startDate = null;
	public String stopDate = null;

	TimeSeriesBase(String stream, String resolution) {
		this(stream, resolution, null);
	}
	
	
	TimeSeriesBase(String stream, String resolution, Proxy proxy) {
		super(proxy);
		this.stream = stream;
		this.resolution = resolution;
	}

	
	public TimeSeries limit(int limit) {
		
		return this;
	}
	

	/* (non-Javadoc)
	 * @see com.infinigongroup.api.TimeSeries#start(java.util.Date)
	 */
	@Override
	public TimeSeries start(Date startDate) {
		
		return start(startDate.toString());
	}

	/* (non-Javadoc)
	 * @see com.infinigongroup.api.TimeSeries#stop(java.util.Date)
	 */
	@Override
	public TimeSeries stop(Date stoptDate) {
		
		return stop(stoptDate.toString());
	}

	/* (non-Javadoc)
	 * @see com.infinigongroup.api.TimeSeries#start(java.lang.String)
	 */
	@Override
	public TimeSeries start(String startDate) {
		this.startDate = startDate;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.infinigongroup.api.TimeSeries#stop(java.lang.String)
	 */
	@Override
	public TimeSeries stop(String stopDate) {
		this.stopDate = stopDate;
		return this;
	}

	public String getUrl() {
		String url = host + getPath() + "/" + stream + "/" + resolution + "/?format=json";
		
			try {
				if (startDate != null)
				url += "&start="+ URLEncoder.encode(startDate, "UTF-8");
			
		if (stopDate != null)
			url += "&stop="+URLEncoder.encode(stopDate, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return url;
		
	}

	abstract String getPath();


	@Override
	protected Object getResults() {
		return responseGet("results");
	}

}