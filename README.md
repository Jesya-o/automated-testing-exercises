# API Testing with REST Assured / UI Testing

## Prerequisites

- JDK 17

## API Testing

### Endpoints:

- GET booking
- GET booking/{id}
- POST booking

### Framework:

Adding abstraction and additional functionality atop third party library.

#### Framework functionality

- Efficient way to communicate with API
- Efficient way to add payload
- Efficient way to validate response
- Efficient way to create payloads with test data
- Tests speak business logic (to some extent)

### Tests

- post authentication with correct credentials returns 200
- post authentication with correct credentials returns 401
- post authentication returns token
- post booking with wrong Accept header returns 418
- put booking should return 200
- delete booking should return 201

## UI Testing

### Testing target:
https://www.saucedemo.com
