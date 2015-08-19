# jinfinigon
A java api for  the Infinigon's Social Analytical service
This api offers three iterable classes Timeline, Tweets, and Snaphots. 

Prequisits: json-simple - https://github.com/fangyidong/json-simple


## Connecting through a proxy.
All the iterators take an optional Proxy object.

```java
	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.38.89.25", 8080));
	
	
```



## Timeline

To get a timeline for a given stream use the iterable Timeline class.
The constructor requires Stream and resolution where:
stream - is the stream id such as AAPL or FDA
resolution  - Data is aggregated by minute, hour or day (for more see Resolutions) so you can use:
M for minutes
H for hours
d for days

#### Returned Value
```json
{"date":"2015-08-19 00:45Z","sentiment":0.0,"tweets":2}
```



see: http://realtime.infinigongroup.com/api/docs/#data_resolutions

```java
// Here AAPL is the stream and M is the resolution.
Timeline timeline = new Timeline("AAPL", "M");
		for (Object timepoint : timeline) {
			System.out.println(timepoint);
		}
```

Using a Proxy: 

```java
	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.38.89.25", 8080));
		Timeline timeline = new Timeline("AAPL", "M", proxy);
		for (Object timepoint : timeline) {
			System.out.println(timepoint);
		}
		
```


## Tweets

You can use the Tweets iterable  to request tweets from any stream for a given period in time.
The constructor requires Stream and resolution where:
Where:
stream
is the stream id such as AAPL or FDA
resolution
Tweets are indexed by minute M or by hour H (for more see http://realtime.infinigongroup.com/api/docs/#data_resolutions
) so you can use:
M - returns your tweets collected in the current minute


```java
// Here AAPL is the stream and M is the resolution.
		Tweets tweets = new Tweets("AAPL", "M", proxy);
		for (Object timepoint : tweets) {
			System.out.println(timepoint);
		}
```


H - returns your tweets collected in the current hour.
You can also give a timepoint
Where timepoint is a java.util.Date. 

```java
		Tweets tweets = new Tweets("AAPL", "M", proxy, someDate);
		for (Object timepoint : tweets) {
			System.out.println(timepoint);
		}
```

#### Returned Value
```json
{"date":"2015-08-19 00:43Z","sentiment":0.62,"tweets":26}
```

## Snapshots

Snapshot - Returns aggregation data for the streams selection of streams. Use this API to generate grids, maps, heat trees and clouds for groups of streams.
```java
		Snapshots snapshot = new Snapshots(proxy);
		for (Object timepoint : snapshot.tags("{DJ30}", "{Energy}*").change_3(5)) {
			System.out.println(timepoint);
		}
```


#### Returned Value
```json
{"postedTime":"2015-08-19T00:46:04.000Z","author":"NBohrquez","text":"RT @somosinocentes: La innovación es lo que distingue a un líder de los demás. (Steve Jobs)","avatar":"https:\/\/pbs.twimg.com\/profile_images\/3285028797\/e6822aeea28084c15995734ab23375c4_normal.jpeg"}
```
