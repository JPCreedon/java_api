# jinfinigon
A java api for  the Infinigon's Social Analytical service
This api offers three iterable classes Timeline, Tweets, and Snaphots. 

Prequisits: json-simple - https://github.com/fangyidong/json-simple


## Connecting through a proxy.
All the iterators take an optional Proxy object.


### Using a Proxy: 

```java
	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.38.89.25", 8080));
	Timeline timeline = new Timeline("AAPL", "M", proxy);
	for (Object timepoint : timeline) {
			System.out.println(timepoint);
	}
		
```



## com.infinigongroup.api.Timeline

The iterable Timeline class yields timepoints of data for a given stream that enable you to build timeline charts for individual streams. 

### Timeline Data


```json
{"date":"2015-08-19 00:45Z","sentiment":0.0,"tweets":2}
```

### Timeline Parameters

##### stream & resolution 
The constructor requires **stream** and **resolution** where:
* **stream** - is the **stream** id such as *AAPL* or *FDA*
* **resolution**  - Data is aggregated by minute, hour or day (for more see [Resolutions](http://realtime.infinigongroup.com/api/docs/#data_resolutions) ) so you can use:
 
 code | resolution
 --- | ---
 M | for minutes
 H | for hours
 d | for days
 
##### start & stop 
Optionally you can give a date and time range by setting **start** and **stop**. **stop** parameter always defaults to now, while **start's** default value depends of the resoluti on given.

 Format | Default Start Value |
 --- | --- 
 M |	24 hours ago
 H |	7 days ago
 d |	30 days ago
    
    
You can specify the dates using [java.utils.Date](https://docs.oracle.com/javase/6/docs/api/java/util/Date.html) or you can also use a String in many formats (see [Date Formats] (http://realtime.infinigongroup.com/api/docs/#data_dates)). All times are by default **UTC** so you must be explicit and add the timezone. You can test your date and time values using: 

##### Time Delta

For the **start** parameter you can also give a *time delta*, specifying a period of time before the given (or default) **stop** date.

Period Code |	Period	| Example | Description 
--- | --- | --- | --- | ---
M |	minutes	| 30M | starting thirty minutes ago
H |	hours	| 8H | starting eight hours ago
d |	days	| 5d | starting five days ago
w |	weeks	| 2w | starting fortnight ago
m |	months	| 3m | starting on the same date 3 months ago
y |	years	| 1y | starting a year ago


### Timeline Examples

##### Reading minute timepoints for AAPL

```java
// Here AAPL is the stream and M is the resolution.
Timeline timeline = new Timeline("AAPL", "M");
		for (Object timepoint : timeline) {
			System.out.println(timepoint);
		}
```
```json
{"date":"2015-08-19 13:00Z","sentiment":0.66,"tweets":50}
{"date":"2015-08-19 13:01Z","sentiment":0.61,"tweets":41}
{"date":"2015-08-19 13:02Z","sentiment":0.73,"tweets":33}
{"date":"2015-08-19 13:03Z","sentiment":0.69,"tweets":29}
{"date":"2015-08-19 13:04Z","sentiment":0.66,"tweets":38}
{"date":"2015-08-19 13:05Z","sentiment":0.65,"tweets":23}
{"date":"2015-08-19 13:06Z","sentiment":0.7,"tweets":33}
```

##### Reading hour of timepoints for AAPL

```java
// Here AAPL is the stream and M is the resolution.
Timeline timeline = new Timeline("AAPL", "H");
		for (Object timepoint : timeline) {
			System.out.println(timepoint);
		}
```
```json
{"date":"2015-08-10 14:00Z","sentiment":0.67,"tweets":2076}
{"date":"2015-08-10 15:00Z","sentiment":0.63,"tweets":2476}
{"date":"2015-08-10 16:00Z","sentiment":0.65,"tweets":2442}
{"date":"2015-08-10 17:00Z","sentiment":0.65,"tweets":2193}
{"date":"2015-08-10 18:00Z","sentiment":0.64,"tweets":2326}
{"date":"2015-08-10 19:00Z","sentiment":0.66,"tweets":2408}
{"date":"2015-08-10 20:00Z","sentiment":0.65,"tweets":2209}
```

##### Reading day of timepoints for AAPL

```java
// Here AAPL is the stream and M is the resolution.
Timeline timeline = new Timeline("AAPL", "d");
for (Object timepoint : timeline) {
	System.out.println(timepoint);
}
```
```json
{"date":"2015-07-22 00:00Z","sentiment":0.62,"tweets":26528}
{"date":"2015-07-23 00:00Z","sentiment":0.64,"tweets":22045}
{"date":"2015-07-24 00:00Z","sentiment":0.65,"tweets":24992}
{"date":"2015-07-25 00:00Z","sentiment":0.63,"tweets":29631}
{"date":"2015-07-26 00:00Z","sentiment":0.42,"tweets":1436}
{"date":"2015-07-27 00:00Z","sentiment":0.61,"tweets":11123}
{"date":"2015-07-28 00:00Z","sentiment":0.65,"tweets":26182}
```


##### Reading last 4 days of day of timepoints for AAPL

```java
// Here AAPL is the stream and M is the resolution.
Timeline timeline = new Timeline("AAPL", "d");
for (Object timepoint : timeline) {
	System.out.println(timepoint);
}
```
```json
{"date":"2015-07-22 00:00Z","sentiment":0.62,"tweets":26528}
{"date":"2015-07-23 00:00Z","sentiment":0.64,"tweets":22045}
{"date":"2015-07-24 00:00Z","sentiment":0.65,"tweets":24992}
{"date":"2015-07-25 00:00Z","sentiment":0.63,"tweets":29631}
{"date":"2015-07-26 00:00Z","sentiment":0.42,"tweets":1436}
{"date":"2015-07-27 00:00Z","sentiment":0.61,"tweets":11123}
{"date":"2015-07-28 00:00Z","sentiment":0.65,"tweets":26182}
```

##### Reading last 4 days of day of timepoints for AAPL

```java
// Here AAPL is the stream and M is the resolution.
Timeline timeline = new Timeline("AAPL", "d").start("4d");
for (Object timepoint : timeline) {
	System.out.println(timepoint);
}
```
```json
{"date":"2015-08-17 00:00Z","sentiment":0.65,"tweets":41620}
{"date":"2015-08-18 00:00Z","sentiment":0.66,"tweets":41506}
{"date":"2015-08-19 00:00Z","sentiment":0.66,"tweets":42823}
{"date":"2015-08-20 00:00Z","sentiment":0.68,"tweets":20481}
```






## com.infinigongroup.api.Tweets

You can use the Tweets iterable  to request tweets from any stream for a given period in time.

### Timeline Parameters

##### stream & resolution
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

## com.infinigongroup.api.Snapshots

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
