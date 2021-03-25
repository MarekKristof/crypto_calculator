# Crypto calculator for Bob's burgeoning fortune

## Overview

This program prints the value of Bob's crypto portfolio.

The input to the program is a file called "bobs_crypto.txt"

Supported cryptocurrencies are :

* BTC
* ETH
* XRP

Supported currencies are :

* EUR
* USD
* JPY

### Get actual exchange rate

For actual exchange rate is used [Crypto compare API].

####  Example request

    curl --location --request POST 'https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=EUR'


####  Example response

    HTTP/1.1 200 OK
    Date: Thu, 25 Mar 2021 10:32:47 GMT
    Status: 200 OK
    Connection: keep-alive
    Content-Type: application/json; charset=UTF-8
    Transfer-Encoding: chunked

```json
{
  "EUR": 47827.57
}
```

### Run the program

- run the main method in App
- run the command: mvn package exec:java

### Sample output

```
Calculating value of portfolio in currency EUR
Actual value of crypto currency Bitcoin is 447139.20 EUR
Actual value of crypto currency Ethereum is 6814.65 EUR
Actual value of crypto currency Ripple is 860.00 EUR
Total value of portfolio is 454813.85 EUR
-------------------------------------------------------------
Calculating value of portfolio in currency JPY
Actual value of crypto currency Bitcoin is 57575044.90 JPY
Actual value of crypto currency Ethereum is 877424.75 JPY
Actual value of crypto currency Ripple is 110880.00 JPY
Total value of portfolio is 58563349.65 JPY
-------------------------------------------------------------
Calculating value of portfolio in currency USD
Actual value of crypto currency Bitcoin is 527065.80 USD
Actual value of crypto currency Ethereum is 8033.80 USD
Actual value of crypto currency Ripple is 1020.00 USD
Total value of portfolio is 536119.60 USD
-------------------------------------------------------------
```

[Crypto compare API]: https://min-api.cryptocompare.com/documentation
