# coinmarket-api

### External API docs:

https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyAirdrops

## Implemented external methods:

### CoinMarketCap ID Map

GET v1/cryptocurrency/map
Parameters:

* start integer
* limit integer [ 1 .. 5000 ]
* sort string
* listing_status string
* symbol string
* aux string

### Quotes Latest v2

GET /v2/cryptocurrency/quotes/latest
Parameters:

* id string
* slug string
* symbol string
* convert string
* convert_id string
* aux string
* skip_invalid boolean

### CoinMarketCap ID Map

GET /v1/fiat/map
Parameters:

* start integer >= 1
* limit integer [ 1 .. 5000 ]
* sort string
* include_metals boolean

### Key Info

GET /v1/key/info
Parameters: NONE