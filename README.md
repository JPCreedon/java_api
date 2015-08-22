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
{	"date":"2015-08-19 00:45Z",
	"sentiment":0.0,
	"tweets":2
}
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
 { 	"postedTime":"2015-08-21T00:42:08.000Z",
 	"author":"DeltaBravo33",
 	"text":"Instagram : by airbus.driver - #Qantas #QantasAirways #melbourneairport #melbourne #boeing #boeing737 #b737 #737 #m\u2026 http:\/\/t.co\/7TgGML73aA",
 	"avatar":"https:\/\/pbs.twimg.com\/profile_images\/589500634149343232\/wcMsP73m_normal.jpg"
 }
```

### `Tweet` Parameters

##### stream 
The constructor requires **stream** and **resolution** where:
* **stream** - is the **stream** id such as *AAPL* or *FDA*



 
##### start & stop 
As with Timeline you can give a date and time range by setting **start** and **stop**. **stop** parameter always defaults to now, while **start's** default value is the start of the current minute.


    
    
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

## `com.infinigongroup.api.Snapshot`


`Snapshot` - Returns aggregation data for a selection of streams. Use this API to generate grids, maps, heat trees and clouds for groups of streams. 


### `Snapshot` Data


```json
{
			"description": "Chevron Corporation",
			"sentiment": 0.558823529411764,
			"tags": [
				"Equities",
				"Energy",
				"SP500",
				"Oil & Gas",
				"DJ30"
			],
			"timestamp": {
				"$date": 1440164723564
			},
			"symbol": "CVX",
			"clout": 1292,
			"words": [
				[
					"chevron",
					18
				],
				[
					"oil",
					3
				],
				[
					"morningword",
					3
				],
				[
					"slick",
					3
				],
				[
					"xom",
					3
				],
				[
					"cvx",
					3
				],
				[
					"negra",
					2
				],
				[
					"loma",
					2
				],
				[
					"superpozo",
					2
				],
				[
					"negro",
					2
				]
			],
			"activity": 34,
			"change_5": 14,  
			"variance": -67,
			"change_3": 69,
			"change_10": 8
		}
```

### `Snapshot` Fields

##### `last_request`
    Gives you the timestamp in java.util.Date  of the last update. 
    
    
### `Snapshot` Parameters


##### `since`
    Use this parameter to retrieve streams that have had activity since some given date. See the documentation on start. See above documentation on `start`.
    


    
##### `streams` 

    You can specify one or more streams, comma delimited, the default is all streams with available data. 
```#java    
for (Object snapshot : new Snapshots().streams("AAPL", "GOOG")) {
    System.out.print(i++ + ". ");
    System.out.println(snapshot);
    if (i == 20) break;
}
```

```#json
0. {"clout":9278,"sentiment":0.6392857143,"symbol":"AAPL","change_10":7,"activity":6537,"change_3":18,"variance":-94,"change_5":18,"words":[["iphone",118.0],["ipad",86.0],["apple",68.0],["gameinsight",36.0],["full",29.0],["steve",27.0],["jobs",27.0],["ipadgames",27.0],["read",19.0],["16gb",17.0]],"description":"Apple Inc","tags":["Personal Computers","Equities","SP500","Technology","PWTRADEWATCHLIST"],"timestamp":{"$date":1440282041210}}
1. {"clout":5976,"sentiment":0.7231638418,"symbol":"GOOG","change_10":13,"activity":3351,"change_3":34,"variance":-92,"change_5":20,"words":[["google",105.0],["play",22.0],["servers",19.0],["allaboutgoogle",19.0],["datacenter",19.0],["loses",18.0],["disponible",18.0],["data",18.0],["app",15.0],["consecutive",15.0]],"description":"Google Inc.","tags":["Equities","SP500","Technology","Internet Information Providers","PWTRADEWATCHLIST"],"timestamp":{"$date":1440282020217}}
```

    
##### `tags`
    Instead of specifying streams you can specify category or groups of streams that we call tags. The notation is a little more developed that just a comma delimited list, so I'm going to show you some examples:

* `{DJ30}{Energy}*`  Streams that are in the energy sector and belong to the DJ30. 
* `<DJ30>` Streams that are NOT in the DJ30.
* `{Energy}<DJ30>*` Streams that are in the energy sector and DO NOT belong to the DJ30.
* `{Energy}<DJ30>*{SP500}|` (Streams that are in energy sector but do not belong to the DJ30) OR ( those that belong to the SP500).


###### Snapshots of streams that are in the energy sector and belong to the DJ30. 

```#java    
for (Object snapshot : new Snapshots().tags("{DJ30}{Energy}*")) {
    System.out.print(i++ + ". ");
    System.out.println(snapshot);
    if (i == 20) break;
}
```

```#json
0. {"clout":1557,"sentiment":0.575,"symbol":"XOM","change_10":-42,"activity":155,"change_3":-47,"variance":-72,"change_5":-39,"words":[["mobil",30.0],["baru",6.0],["ini",6.0],["drive",6.0],["coba",6.0],["mau",6.0],["test",6.0],["iims",6.0],["pameran",6.0],["bisa",6.0]],"description":"Exxon Mobil Corporation","tags":["Equities","Energy","SP500","Oil & Gas","DJ30"],"timestamp":{"$date":1440282187760}}
1. {"clout":1356,"sentiment":0.5666666667,"symbol":"CVX","change_10":15,"activity":30,"change_3":-23,"variance":25,"change_5":15,"words":[["chevron",26.0],["etsy",7.0],["blue",4.0],["via",2.0],["pnnfi0xrsb",2.0],["yellow",2.0],["female",2.0],["help",2.0],["transfer",2.0],["white",2.0]],"description":"Chevron Corporation","tags":["Equities","Energy","SP500","Oil & Gas","DJ30"],"timestamp":{"$date":1440282211473}}

```


###### Snapshots of streams that are in the energy sector and DO NOT belong to the DJ30.

```#java    
for (Object snapshot : new Snapshots().tags("{Energy}<DJ30>*")) {
    System.out.print(i++ + ". ");
    System.out.println(snapshot);
    if (i == 20) break;
}
```

```#json

0. {"clout":98,"sentiment":0.0,"symbol":"SGY","change_10":3500,"activity":2,"change_3":-100,"variance":0,"change_5":-100,"words":[],"description":"Stone Energy Corp.","tags":["Equities","Energy","Independent Oil & Gas","AUTOGEN","NYSE"],"timestamp":{"$date":1440282083093}}
1. {"clout":93,"sentiment":0.25,"symbol":"E","change_10":-26,"activity":4,"change_3":-100,"variance":-13,"change_5":-100,"words":[],"description":"Eni S.P.A.","tags":["Major Integrated Oil & Gas","AUTOGEN","Equities","Energy","PWTRADEWATCHLIST","NYSE"],"timestamp":{"$date":1440282247729}}

```




As you can see we are using Reverse Polish Notation.

    Sets of tags you want are defined using comma delimited tags in braces {}
    Sets of tags you don't want are defined using comma delimited tags in braces angle brackets <>
    You can union (or OR) your sets using the | pipe symbol.
    You can intersect (or AND) your sets using the * star symbol.

Just remember in Reverse Polish Notation

    operandA operandB operator = operandA operator operandB
    operandA operandB operator1 operandC operator2 = (operandA operator1 operandB) operator2 operandC
    operandA operandB operator1 operandC operandD operator2 operator3 = (operandA operator1 operandB) operator3 (operandC operator2 operandD)

For more on RPN see http://en.wikipedia.org/wiki/Reverse_Polish_notation.

##### `words`
    Just as with `tags` you can filter by words in the word cloud using our RPN. The setting is character case insensitive. For instance 
    '{buy}<best buy>*{fda}{approval}*|`
Returns the data for streams whose word cloud **contains**  `buy` but **not** `best buy`, **or**  any streams  whose word cloud **contains** `fda` **and** `approval`. 
    
    
##### `change_3`
    Use this parameter to filter by **percent change** in the last **3 minutes**. 

##### `change_5`
    Use this parameter to filter by **percent change** in the last **5 minutes**. 
    
##### `change_10`
    Use this parameter to filter by **percent change** in the last **10 minutes**. 
    
##### `variance`
    Use this parameter to filter by **variance** for the hour. So if you used a value of 30 the Snapshot iterator would return data for streams that have 30% or more tweets than the average for that stream for the given hour for that day. This is useful for consumer companies such as Mac Donald's that typically have a higher rate at lunch times and in the evenings.
    
    
##### `fields`
    You can specify which fields you want to retrieve using a comma delimited list. By default you get them all. The available fields are:

* activity
* change_3
* change_5
* change_10
* description
* sentiment
* variance
* words
* tags

##### `order`
    You can use the order parameter to return the data sorted by a field, for example:

 * `variance`  Returns the data in ascending order
 * `-variance` Returns the data in descending order


### `Snapshots` Examples

##### Snapshots where variance 

