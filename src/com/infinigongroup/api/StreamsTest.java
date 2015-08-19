package com.infinigongroup.api;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class StreamsTest {
	public static void main(String[] args) throws Exception {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.38.89.25", 8080));
		Timeline timeline = new Timeline("AAPL", "M", proxy);
		for (Object timepoint : timeline) {
			System.out.println(timepoint);
		}

		Tweets tweets = new Tweets("AAPL", "M", proxy);
		for (Object timepoint : tweets) {
			System.out.println(timepoint);
		}

		Snapshots snapshot = new Snapshots(proxy);
		for (Object timepoint : snapshot.tags("{DJ30}", "{Energy}*").change_3(5)) {
			System.out.println(timepoint);
		}

	}
}
