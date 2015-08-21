# jinfinigon
A java api for  the Infinigon's Social Analytical service
This api offers three iterable classes Timeline, Tweets, and Snaphots. 

Prequisits: json-simple - https://github.com/fangyidong/json-simple


## Connecting through a proxy.
All the iterators take an optional Proxy object.


### Using a Proxy: 

```java
Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.38.89.25", 8080));
TimeSeries timeline = new Timeline("AAPL", Timeline.M, proxy);
for (Object timepoint : timeline) {
	System.out.println(timepoint);
}
		
```



## `com.infinigongroup.api.Timeline`

The iterable `Timeline` class yields timepoints of data for a given stream that enable you to build timeline charts for individual streams. 

### `Timeline` Data


```json
{"date":"2015-08-19 00:45Z","sentiment":0.0,"tweets":2}
```

### `Timeline` Parameters

##### stream & resolution 
The constructor requires **stream** and **resolution** where:
* **stream** - is the **stream** id such as *AAPL* or *FDA*
* **resolution**  - Data is aggregated by minute, hour or day (for more see [Resolutions](http://realtime.infinigongroup.com/api/docs/#data_resolutions) ) so you can use:
 
 code | resolution
 --- | ---
 `TimeSeries.M` | for minutes
 `TimeSeries.H` | for hours
 `TimeSeries.d` | for days
 
##### start & stop 
Optionally you can give a date and time range by setting **start** and **stop**. **stop** parameter always defaults to now, while **start's** default value depends of the resolution on given.

 Format | Default Start Value |
 --- | --- 
 `TimeSeries.M` |	24 hours ago
 `TimeSeries.H` |	7 days ago
 `TimeSeries.d` |	30 days ago
    
    
You can specify the dates using [`java.utils.Date`](https://docs.oracle.com/javase/6/docs/api/java/util/Date.html) or you can also use a String in many formats (see [Date Formats] (http://realtime.infinigongroup.com/api/docs/#data_dates)). All times are by default **`UTC`** so you must be explicit and add the timezone. You can test your date and time values using: 

##### Time Delta

For the **start** parameter you can also give a *time delta*, specifying a period of time before the given (or default) **stop** date.

Period Code |	Period	| Example | Description 
--- | --- | --- | --- | ---
M |	minutes	| `"30M"` | starting thirty minutes ago
H |	hours	| `"8H"` | starting eight hours ago
d |	days	|`"5d"` | starting five days ago
w |	weeks	| `"2w"` | starting fortnight ago
m |	months	| `"3m"` | starting on the same date 3 months ago
y |	years	| `"1y"` | starting a year ago


### `Timeline` Examples

##### Reading minute timepoints for AAPL

```java
TimeSeries timeline = new Timeline("AAPL", TimeSeries.M, proxy);
int i =0;
for (Object timepoint : timeline) {
	System.out.print(i++ + ". ");
	System.out.println(timepoint);
}
```
```json
0. {"date":"2015-08-19 16:35Z","sentiment":0.67,"tweets":18}
1. {"date":"2015-08-19 16:36Z","sentiment":0.64,"tweets":39}
2. {"date":"2015-08-19 16:37Z","sentiment":0.67,"tweets":21}
3. {"date":"2015-08-19 16:38Z","sentiment":0.73,"tweets":37}
4. {"date":"2015-08-19 16:39Z","sentiment":0.63,"tweets":52}
5. {"date":"2015-08-19 16:40Z","sentiment":0.61,"tweets":28}
6. {"date":"2015-08-19 16:41Z","sentiment":0.66,"tweets":32}
7. {"date":"2015-08-19 16:42Z","sentiment":0.65,"tweets":37}
8. {"date":"2015-08-19 16:43Z","sentiment":0.63,"tweets":30}
9. {"date":"2015-08-19 16:44Z","sentiment":0.7,"tweets":44}
```

##### Reading hour of timepoints for AAPL

```java
TimeSeries timeline = new Timeline("AAPL", TimeSeries.H, proxy);
int i =0;
for (Object timepoint : timeline) {
	System.out.print(i++ + ". ");
	System.out.println(timepoint);
}
```
```json
0. {"date":"2015-08-10 17:00Z","sentiment":0.65,"tweets":2193}
1. {"date":"2015-08-10 18:00Z","sentiment":0.64,"tweets":2326}
2. {"date":"2015-08-10 19:00Z","sentiment":0.66,"tweets":2408}
3. {"date":"2015-08-10 20:00Z","sentiment":0.65,"tweets":2209}
4. {"date":"2015-08-10 21:00Z","sentiment":0.62,"tweets":1974}
5. {"date":"2015-08-10 22:00Z","sentiment":0.63,"tweets":1857}
6. {"date":"2015-08-10 23:00Z","sentiment":0.64,"tweets":1762}
7. {"date":"2015-08-11 00:00Z","sentiment":0.63,"tweets":1671}
8. {"date":"2015-08-11 01:00Z","sentiment":0.63,"tweets":1665}
9. {"date":"2015-08-11 02:00Z","sentiment":0.63,"tweets":1874}
```

##### Reading day of timepoints for AAPL

```java
TimeSeries timeline = new Timeline("AAPL", TimeSeries.d, proxy);
int i =0;
for (Object timepoint : timeline) {
	System.out.print(i++ + ". ");
	System.out.println(timepoint);
}
```
```json
0. {"date":"2015-07-22 00:00Z","sentiment":0.62,"tweets":26528}
1. {"date":"2015-07-23 00:00Z","sentiment":0.64,"tweets":22045}
2. {"date":"2015-07-24 00:00Z","sentiment":0.65,"tweets":24992}
3. {"date":"2015-07-25 00:00Z","sentiment":0.63,"tweets":29631}
4. {"date":"2015-07-26 00:00Z","sentiment":0.42,"tweets":1436}
5. {"date":"2015-07-27 00:00Z","sentiment":0.61,"tweets":11123}
6. {"date":"2015-07-28 00:00Z","sentiment":0.65,"tweets":26182}
7. {"date":"2015-07-29 00:00Z","sentiment":0.66,"tweets":24080}
8. {"date":"2015-07-30 00:00Z","sentiment":0.66,"tweets":42599}
9. {"date":"2015-07-31 00:00Z","sentiment":0.63,"tweets":52068
```


##### Reading last 4 days of day of timepoints for AAPL

```java
TimeSeries timeline = new Timeline("AAPL", TimeSeries.d, proxy).start("4d");
int i =0;
for (Object timepoint : timeline) {
	System.out.print(i++ + ". ");
	System.out.println(timepoint);
}
```
```json
0. {"date":"2015-08-17 00:00Z","sentiment":0.65,"tweets":41620}
1. {"date":"2015-08-18 00:00Z","sentiment":0.66,"tweets":41506}
2. {"date":"2015-08-19 00:00Z","sentiment":0.66,"tweets":42823}
3. {"date":"2015-08-20 00:00Z","sentiment":0.68,"tweets":28244}
```

##### Reading minute data from a given time of day for AAPL

```java
TimeSeries timeline = new Timeline("AAPL", TimeSeries.M).start("2015-08-19 12:34 EST").stop("2015-08-19 12:41 EST");
int i =0;
for (Object timepoint : timeline) {
	System.out.print(i++ + ". ");
	System.out.println(timepoint);
}
```
```json
0. {"date":"2015-08-19 17:34Z","sentiment":0.66,"tweets":32}
1. {"date":"2015-08-19 17:35Z","sentiment":0.61,"tweets":36}
2. {"date":"2015-08-19 17:36Z","sentiment":0.74,"tweets":31}
3. {"date":"2015-08-19 17:37Z","sentiment":0.68,"tweets":41}
4. {"date":"2015-08-19 17:38Z","sentiment":0.72,"tweets":36}
5. {"date":"2015-08-19 17:39Z","sentiment":0.53,"tweets":19}
6. {"date":"2015-08-19 17:40Z","sentiment":0.72,"tweets":32}
7. {"date":"2015-08-19 17:41Z","sentiment":0.6,"tweets":35}
```


## `com.infinigongroup.api.Tweets`

You can use the Tweets iterator  to request tweets from any stream for a given period in time.

### `Tweet` Data

```json
{"date":"2015-08-19 00:43Z","sentiment":0.62,"tweets":26}
```

### `Tweet` Parameters

##### stream & resolution 
The constructor requires **stream** and **resolution** where:
* **stream** - is the **stream** id such as *AAPL* or *FDA*
* **resolution**  - Tweets are indexed by minute M or by hour H (for more see [Resolutions](http://realtime.infinigongroup.com/api/docs/#data_resolutions) ) so you can use:

 Using code | Returns
 --- | ---
 `Tweet.M` |   tweets collected in the current minute
 `Tweet.H` |  tweets collected in the current hour


 
##### start
You can optionally give a valid start from which to collect tweets.  You can specify the start using [`java.utils.Date`](https://docs.oracle.com/javase/6/docs/api/java/util/Date.html) or you can also use a String in many formats (see [Date Formats] (http://realtime.infinigongroup.com/api/docs/#data_dates)). All times are by default **`UTC`** so you must be explicit and add the timezone. You can test your date and time values using: 

##### Time Delta

For the **start** parameter you can also give a *time delta*, specifying a period of time before your current time.

Period Code |	Period	| Example | Description 
--- | --- | --- | --- | ---
M |	minutes	| `"30M"` | starting thirty minutes ago
H |	hours	| `"8H"` | starting eight hours ago
d |	days	|`"5d"` | starting five days ago
w |	weeks	| `"2w"` | starting fortnight ago
m |	months	| `"3m"` | starting on the same date 3 months ago
y |	years	| `"1y"` | starting a year ago 

 


### `Tweet` Examples

##### Reading minute tweets for BA

```java
TimeSeries tweets = new Tweets("FB", TimeSeries.M, proxy);
int i =0;
for (Object tweet : tweets) {
	System.out.print(i++ + ". ");
	System.out.println(tweet);
}
```
```json
0. {"postedTime":"2015-08-21T00:42:00.000Z","author":"DeltaBravo33","text":"Instagram : by world_aviation99 - Good night!! Lufthansa Cargo Boeing 777F landing at Frankfurt intl. #Boeing #avpo\u2026 http:\/\/t.co\/iZWmjWSuUz","avatar":"https:\/\/pbs.twimg.com\/profile_images\/589500634149343232\/wcMsP73m_normal.jpg"}
1. {"postedTime":"2015-08-21T00:42:05.000Z","author":"kevinagipavuc","text":"RT @josephuhanokov: ? ??????? ?????? ??????????? ?? ?????????? Boeing ??? ????????? ???????? ?? ???????","avatar":"https:\/\/pbs.twimg.com\/profile_images\/523784246427537408\/OqWKikgU_normal.png"}
2. {"postedTime":"2015-08-21T00:42:08.000Z","author":"DeltaBravo33","text":"Instagram : by world_aviation99 - Good night!! Lufthansa Cargo Boeing 777F landing at Frankfurt intl. #Boeing #avpo\u2026 http:\/\/t.co\/0FZsSYizdK","avatar":"https:\/\/pbs.twimg.com\/profile_images\/589500634149343232\/wcMsP73m_normal.jpg"}
3. {"postedTime":"2015-08-21T00:42:08.000Z","author":"DeltaBravo33","text":"Instagram : by airbus.driver - #Qantas #QantasAirways #melbourneairport #melbourne #boeing #boeing737 #b737 #737 #m\u2026 http:\/\/t.co\/7TgGML73aA","avatar":"https:\/\/pbs.twimg.com\/profile_images\/589500634149343232\/wcMsP73m_normal.jpg"}


```

