
# Event  Sourced Process Managers

## Actors

- Waiter
    - takes order from customer
    - writes items to document
- Cook
    - makes the food
    - writes time taken and ingredients to document 
- Assistant manager 
    - calculates tax and totals, writes them to document
- Cashier
    - writes "paid" to document
    - puts document on spike
- Manager
    - takes and stores documents at the end of the day

## Passing a document through microservices

- Each microservice should pass on unchanged all data it doesn't know 
    - Wrap JSON in an object which provides accessors which are backed by the JSON instead of fields
    - Do not change or remove unknown data from the JSON