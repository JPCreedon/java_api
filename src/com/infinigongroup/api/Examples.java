package com.infinigongroup.api;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class Examples {

	public Examples() {
	}

	public static void main(String[] args) throws Exception {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.38.89.25", 8080));

//		TimeSeries timeline = new Timeline("AAPL", Timeline.M, proxy);
//		int i =0;
//		for (Object timepoint : timeline) {
//			System.out.print(i++ + ". ");
//			System.out.println(timepoint);
//		}
//
//		timeline = new Timeline("AAPL", Timeline.H, proxy);
//		i =0;
//		for (Object timepoint : timeline) {
//			System.out.print(i++ + ". ");
//			System.out.println(timepoint);
//		}
//
//
//		timeline = new Timeline("AAPL", Timeline.d, proxy);
//		i =0;
//		for (Object timepoint : timeline) {
//			System.out.print(i++ + ". ");
//			System.out.println(timepoint);
//		}
//
//		timeline = new Timeline("AAPL", Timeline.d, proxy);
//		i =0;
//		for (Object timepoint : timeline) {
//			System.out.print(i++ + ". ");
//			System.out.println(timepoint);
//		}
//		
//		timeline = new Timeline("AAPL", Timeline.d, proxy).start("4d");
//		i =0;
//		for (Object timepoint : timeline) {
//			System.out.print(i++ + ". ");
//			System.out.println(timepoint);
//		}
//		timeline = new Timeline("AAPL", Timeline.M, proxy).start("2015-08-19 12:34 EST").stop("2015-08-19 12:41 EST");
//		i =0;
//		for (Object timepoint : timeline) {
//			System.out.print(i++ + ". ");
//			System.out.println(timepoint);
//		}
//		
//		
		
		
		
		
		TimeSeries tweets = new Tweets("FB", Timeline.M, proxy);
		int i =0;
		for (Object tweet : tweets) {
			System.out.print(i++ + ". ");
			System.out.println(tweet);
		}

		tweets = new Tweets("FB", Timeline.H, proxy);
		i =10;
		for (Object tweet : tweets) {
			System.out.print(i++ + ". ");
			System.out.println(tweet);
		}


		tweets = new Tweets("FB", Timeline.d, proxy);
		i =20;
		for (Object tweet : tweets) {
			System.out.print(i++ + ". ");
			System.out.println(tweet);
		}

		
		tweets = new Tweets("BA", Timeline.d, proxy).start("4d");
		i =30;
		for (Object tweet : tweets) {
			System.out.print(i++ + ". ");
			System.out.println(tweet);
		}
		tweets = new Tweets("BA", Timeline.M, proxy).start("2015-08-19 12:34 EST").stop("2015-08-19 12:41 EST");
		i =40;
		for (Object tweet : tweets) {
			System.out.print(i++ + ". ");
			System.out.println(tweet);
		}
		
		
		
		
	}


}
