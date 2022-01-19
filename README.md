# Java-Test-Task

1. Collecting cryptocurrency data prices from CEX.IO (last price of BTC/USD, ETH/USD and XRP/USD). This data stores in database.
2. Rest Endpoints.
	- GET /cryptocurrencies/minprice?name=[currency_name] - return record with the lowest price of selected cryptocurrency.
	- GET /cryptocurrencies/maxprice?name=[currency_name] - return record with the highest price of selected cryptocurrency. 
	- GET /cryptocurrencies?name=[currency_name]&page=[page_number]&size=[page_size] - return a selected page with selected number of elements and default sorting should be by price from lowest to highest.  
If some other value is provided then appropriate error message throws.
3. Generate a CSV report.
GET /cryptocurrencies/csv - generate a CSV report saved into file. Report contain the following fields: Cryptocurrency Name, Min Price, Max Price.

Also, I've created a .cmd file to launch commands that run console application that gets the last price of currency pairs from CEX.IO and saves them to the database. This .cmd file can be launched in the task scheduler to get information and save it periodically (for example, every half an hour).
