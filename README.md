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
* Spock for unit tests (written in Groovy)
* Gradle build system (with included gradlew-wrapper support)

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

To get a total for three Apples and an Orange, you could execute: 

`http POST http://localhost:8080/cart-total items:='["Apple","Apple","Orange","Apple"]'`

The output should be 

`{
    "cart": {
        "Apple": 3,
        "Orange": 1
    },
    "currency": "GBP",
    "total": 2.05
}`
