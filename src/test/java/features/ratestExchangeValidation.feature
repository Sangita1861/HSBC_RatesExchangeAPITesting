Feature: Validating foreign rates exchange APIs

Scenario: Verify if latest foreign rates exchange API succesfully getting fetched
     Given "LatestRatesAPI" base URI
     When user calls "LatestRatesAPI" with GET http request
     Then the user gets response with status code 200
     And the "base" in response is "EUR"
     And the value of "HKD" is "9.1972"
     
Scenario: Verify if latest foreign rates exchange API return expected error message when invalid resource URL provided
     Given "LatestRatesInvalidAPI" base URI
     When user calls "LatestRatesInvalidAPI" with GET http request
     Then the user gets response with status code 400
     And the response contains expected error message in "error"
  
Scenario: Verify if latest foreign rates exchange API succesfully getting fetched
     Given "PastRatesAPI" base URI
     When user calls "PastRatesAPI" with GET http request
     Then the user gets response with status code 200
     And the "base" in response is "EUR"  
  
Scenario: Verify if Past foreign rates exchange API succesfully getting fetched for specific date
     Given "PastRatesAPI" base URI 
     When user calls "PastRatesAPI" with GET http request for date "2010-01-12"
     Then the user gets response with status code 200
     And the "base" in response is "EUR"  
     
Scenario: Verify if Past foreign rates exchange API succesfully getting fetched for future date
     Given "PastRatesAPI" base URI 
     When user calls "PastRatesAPI" with GET http request for date "2020-12-12"
     Then the user gets response with status code 200
     And the "base" in response is "EUR" 
     And the "PastRatesAPI" future date response matches with response of current date "2020-11-21"
     
Scenario: Verify if Past foreign rates exchange API succesfully getting fetched for specific symbol
     Given "PastRatesSymbolAPI" base URI  for specific "symbols" "USD"
     When user calls "PastRatesSymbolAPI" with GET http request for date "2020-12-12"
     Then the user gets response with status code 200
     And the "base" in response is "EUR" 
     
Scenario: Verify if Past foreign rates exchange API succesfully getting fetched for specific muliple symbols
     Given "PastRatesSymbolAPI" base URI  for specific "symbols" "USD,GBP"
     When user calls "PastRatesSymbolAPI" with GET http request for date "2020-12-12"
     Then the user gets response with status code 200
     And the "base" in response is "EUR" 
     
Scenario: Verify if Past foreign rates exchange API succesfully getting fetched for specific base
     Given "PastRatesBaseAPI" base URI  for specific "base" "GBP"
     When user calls "PastRatesBaseAPI" with GET http request
     Then the user gets response with status code 200
     And the "base" in response is "GBP" 
     
Scenario: Verify if Past foreign rates exchange API succesfully getting fetched for specific muliple symbols
     Given "LatestRatesSymbolAPI" base URI  for specific "symbols" "USD,GBP"
     When user calls "LatestRatesSymbolAPI" with GET http request
     Then the user gets response with status code 200
     And the "base" in response is "EUR" 
     
Scenario: Verify if Past foreign rates exchange API succesfully getting fetched for specific base
     Given "LatestRatesBaseAPI" base URI  for specific "base" "USD"
     When user calls "LatestRatesBaseAPI" with GET http request
     Then the user gets response with status code 200
     And the "base" in response is "USD" 
     
Scenario: Verify if Past foreign rates exchange API succesfully getting fetched for specific symbol
     Given "PastRatesSymbolAPI" base URI  for specific "symbols" "INR"
     When user calls "PastRatesSymbolAPI" with GET http request
     Then the user gets response with status code 200
     And the "base" in response is "EUR" 
     
Scenario: Verify if Past foreign rates exchange API succesfully getting fetched for specific symbol
     Given "PastRatesSymbolAPI" base URI  for specific "symbols" "INR" and "base" "USD"
     When user calls "PastRatesSymbolAPI" with GET http request
     Then the user gets response with status code 200
     And the "base" in response is "USD"
     
 