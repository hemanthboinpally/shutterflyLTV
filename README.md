<b>shutterflyLTV - SimpleLTV Calculation</b>

The only external JAR -gson,  is used for parsing the JSON data as per the requirement.

<b>Assumptions: </b>

•	The time format provided would be coordinated universal time (UTC), as provided ealier in the sample input file. This gives us capability to display the time zone according to the requirement of the business.

•	The events data retrieved from the data warehouse is SCD – 2 type data. This means each event holds the complete information of the event and not just the update column information. 

•	The data should be complete. 

•	The key fields are like surrogate keys, therefore unique.

•	All the data collected is after the date, Shutterfly was founded.

•	Each data store created would appropriately hold the oldest date of event inserted and the latest date. The week calculation is based on this start date and end date of the data store.

•	At least one week data has to be provided, otherwise LTV will be zero. Since number of weeks would be zero.

•	The entire event information is ingested into the datastore. This gives us capability to implement or calculate other business insights.

•	The currency codes used in the dataset is followed by ISO 4217 standard.



