package com.infinigongroup.api;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class StreamsTest {
	public static void main(String[] args) throws Exception {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.38.89.25", 8080));
		TimeSeries timeline = new Timeline("AAPL", "M", proxy).start("2015-08-19 12:34 EST").stop("2015-08-19 12:51 EST").limit(3);
		for (Object timepoint : timeline) {
			System.out.println(timepoint);
		}

//		Tweets tweets = new Tweets("AAPL", "d", proxy);
//		for (Object timepoint : tweets) {
//			System.out.println(timepoint);
//		}
//
		Snapshots snapshot = new Snapshots(proxy);
		for (Object timepoint : snapshot.tags("{DJ30}", "{Energy}*")) {
			System.out.println(timepoint);
		}

	}
}
