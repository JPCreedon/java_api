package com.infinigongroup.api;

import java.net.Proxy;

public class Examples {

    public Examples() {
    }

    public static void main(String[] args) throws Exception {
        Proxy proxy = null; //new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.38.89.25", 8080));
        int i = 0;

        i = 0;

        Tags tags = new Tags();
        for (Object tag : tags) {
            System.out.print(i++ + ". ");
            System.out.println(tag);
            if (i == 20) break;
        }
        i = 0;
        for (Object stream : new Streams()) {
            System.out.print(i++ + ". ");
            System.out.println(stream);
            if (i == 20) break;
        }


        TimeSeries timeline = new Timeline("AAPL", Timeline.M, proxy);
        i = 0;
        for (Object timepoint : timeline) {
            System.out.print(i++ + ". ");
            System.out.println(timepoint);
            if (i == 20) break;
        }

        timeline = new Timeline("AAPL", Timeline.H, proxy);
        i = 0;
        for (Object timepoint : timeline) {
            System.out.print(i++ + ". ");
            System.out.println(timepoint);
            if (i == 20) break;
        }


        timeline = new Timeline("AAPL", Timeline.d, proxy);
        i = 0;
        for (Object timepoint : timeline) {
            System.out.print(i++ + ". ");
            System.out.println(timepoint);
            if (i == 20) break;
        }

        timeline = new Timeline("AAPL", Timeline.d, proxy);
        i = 0;
        for (Object timepoint : timeline) {
            System.out.print(i++ + ". ");
            System.out.println(timepoint);
            if (i == 20) break;
        }

        timeline = new Timeline("AAPL", Timeline.d, proxy).start("4d");
        i = 0;
        for (Object timepoint : timeline) {
            System.out.print(i++ + ". ");
            System.out.println(timepoint);
            if (i == 20) break;
        }
        timeline = new Timeline("AAPL", Timeline.M, proxy).start("2015-08-19 12:34 EST").stop("2015-08-19 12:41 EST");
        i = 0;
        for (Object timepoint : timeline) {
            System.out.print(i++ + ". ");
            System.out.println(timepoint);
            if (i == 20) break;
        }


        TimeSeries tweets = new Tweets("AAPL", Timeline.M, proxy);
        i = 0;
        for (Object tweet : tweets) {
            System.out.print(i++ + ". ");
            System.out.println(tweet);
            if (i == 20) break;
        }


        tweets = new Tweets("BA", Timeline.d, proxy).start("4d");
        i = 30;
        for (Object tweet : tweets) {
            System.out.print(i++ + ". ");
            System.out.println(tweet);
            if (i == 20) break;
        }
        tweets = new Tweets("BA", Timeline.M, proxy).start("2015-08-19 12:34 EST").stop("2015-08-19 12:41 EST");
        i = 40;
        for (Object tweet : tweets) {
            System.out.print(i++ + ". ");
            System.out.println(tweet);
            if (i == 20) break;
        }


    }


}
