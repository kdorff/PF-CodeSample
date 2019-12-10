# PF-CodeSample


## Pre-requisites

* Java 1.8+
* httpie  
  * To submit http requests to exercise the code. Postman could be used, as well.

## Getting the soruce code

* Fork and clone https://github.com/kdorff/PF-CodeSample

## Technologies used

* Java
* Spring-Boot
* Spring-Dependency Injection
* Joda-Money for money handling
* Spock for unit tests (tests written in Groovy)
* Gradle for the build system (with included gradlew support)

## Running the unit tests

To run the unit tests

`./gradlew clean test`

To review the unit test results reports

`open build/reports/tests/test/index.html`

## Executing the code

To execute the application running, run

`./gradlew bootRun`

Once the application is running, it will be listening for web 
requests on the endpoint `http://localhost:8080/cart-total` for a list of cart 
items (in a JSON object with the key of `items`) and returns a JSON payload containing 
* The cart (items with associated quantities)  and a 
total for the items in the cart.

## Example Execution

In this iteration, there are two specials:

* Buy one, get one free on Apples
* Oranges are 3 for the price of 2

To get a total for four Apples and two Oranges, you could execute: 

`http POST http://localhost:8080/cart-total items:='["Apple","Apple","Orange","Apple", "Apple", "Orange", "Orange"]'`

The output should be 

```
{
     "cart": [
         "Apple ( 4 @ GBP 0.60) : GBP 2.40",
         "Orange ( 3 @ GBP 0.25) : GBP 0.75",
         "Free Apple ( 2 @ GBP -0.60) : GBP -1.20",
         "Free Orange ( 1 @ GBP -0.25) : GBP -0.25"
     ],
     "total": "GBP 1.70"
 }
 ```

