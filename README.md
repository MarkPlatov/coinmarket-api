# coinmarket-api

Microservice for integration with https://coinmarketcap.com/converter/

## Databases:

Implemented database storage using Hibernate.

Migration tool - `liquibase`

* `PostgreSQL` - runtime
* `H2` - tests

## Swagger

Endpoint: http://localhost:8080/swagger-ui/index.html

## REST endpoints:

* `GET /integration/update-coins-cache` - refresh coins cache in DB
* `GET /integration/key-info` - get info about integration key limits

## ActiveMQ topics:

* in (get Message with request):
    * `exchange.rate.request`
    * `coin.request`
* out (redirect Message with new payload (aka response)):
    * `exchange.rate.response`
    * `coin.response`

`exchange.rate.` - to get exchange rate by currencies cmc id
(at first search requested exchange rate. If it is not found or overdue
then make integration request, and save result to db)

`coin.` - to find allowed currencies by its name part

## Containerization

Here the `Dockerfile` in the project root for this MS
And `docker-compose.yml` to run full environment

## External API docs:

https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyAirdrops

## Implemented external methods:

* CoinMarketCap ID Map `GET v1/cryptocurrency/map`
* Quotes Latest v2 `GET /v2/cryptocurrency/quotes/latest`
* CoinMarketCap ID Map `GET /v1/fiat/map`
* Key Info `GET /v1/key/info`

