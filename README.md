# API Documentation

### To Build and run the project : 
>```mvn spring-boot:run```

### To Run the Unit Tests
>```mvn test ```

## Testing the API 

Test data for user and purchases has been injected into the project's embedded database for testing and demonstration purposes. Please utilize the following information to evaluate the implementation. Additionally, the user registration functionality is also available.

### Test User Information
#### Login credentials
```
username = demouser
password = demouser
```
#### Test User ID
```
user_id = 1
```

## End Points

## Registering an User

Registers a new user to the system.

>**Endpoint:** `POST /api/auth/register`

### Request

```
http
POST /api/auth/register
Content-Type: application/json

{
  "username": "demouser",
  "password": "demouser"
}
```

### Response
```
HTTP/1.1 200 OK
Content-Type: application/json

{
  "message": "User registered successfully"
}

```

#### Example Curl Request
```
curl --location --request POST 'http://localhost:8080/api/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username" : "demouser",
    "password" : "demouser"
}'
```


## User Login

Authenticates a user and returns an access token.

>**Endpoint:** `POST /api/auth/login`

### Request

```
http
POST /api/auth/login
Content-Type: application/json

{
  "username": "demouser",
  "password": "demouser"
}
```

#### Example Request


### Response

```HTTP/1.1 200 OK
Content-Type: application/json

{
  "accessToken": <access_token>,
  "tokenType": "Bearer"
}
```

### Example Curl Request
```
curl --location --request POST 'http://localhost:8080/api/users/1/purchase/create' \
--header 'Authorization: Bearer <token>' \
--header 'Content-Type: application/json' \
--data-raw '{
    "purchase_item" : "demo",
    "purchase_amount" : 200
}'
```

## Create Purchase
Creates a new purchase for a user.

>**Endpoint:** `POST /api/users/{userId}/purchase/create`

### Request
```
POST /api/users/{userId}/purchase/create
Content-Type: application/json
Authorization: Bearer <access_token>

{
  "purchase_item": "demo",
  "purchase_amount": 200
}
```
### Response
```
HTTP/1.1 200 OK
Content-Type: application/json

{
  "id": 5,
  "purchase_item": "demo",
  "purchase_amount": 200,
  "purchase_date": "2023-07-05T01:31:12.670+00:00",
  "points": 250
}
```
#### Exmaple Curl Request
```
curl --location --request POST 'http://localhost:8080/api/users/1/purchase/create' \
--header 'Authorization: Bearer <token>' \
--header 'Content-Type: application/json' \
--data-raw '{
    "purchase_item" : "demo",
    "purchase_amount" : 200
}'
```

## Calculate Points to Give Purchase Value
Calculates the points to give based on the purchase amount.

>**Endpoint:** `POST /api/users/{userId}/points/calculatePoints`

### Request
```
POST /api/users/{userId}/points/calculatePoints
Content-Type: application/json
Authorization: Bearer <access_token>

{
  "purchase_amount": 120
}
```

### Response
```
HTTP/1.1 200 OK
Content-Type: application/json

{
  "user_id": 1,
  "user_points": 90
}
```

#### Exmaple Curl Request
```
curl --location --request POST 'http://localhost:8080/api/users/1/points/claculatePoints' \
--header 'Authorization: Bearer <token>' \
--header 'Content-Type: application/json' \
--data-raw '{
    "purchase_amount" : 120
}'
```

## Get User Points
Retrieves the total points for a user.

>**Endpoint:** `GET /api/users/{userId}/points/getPoints`

### Request
```
GET /api/users/{userId}/points/getPoints
Authorization: Bearer <access_token>
```
### Response
```
HTTP/1.1 200 OK
Content-Type: application/json

{
  "user_id": 1,
  "user_points": 520
}
```

### Example Curl Requeset
```
curl --location --request GET 'http://localhost:8080/api/users/1/points/getPoints' \
--header 'Authorization: Bearer <token>' \
--data-raw ''
```

## Calculate Monthly Points
Calculates the aggregated points per month for a given date range.

>**Endpoint:** `GET /api/users/{userId}/points/calculateMonthlyPoints`

### Request
```
GET /api/users/1/points/calculateMonthlyPoints?startDate=2023-01-01&endDate=2023-07-30
Authorization: <access_token>

```

### Response
```
HTTP/1.1 200 OK
Content-Type: application/json

[
    {
        "period": "2023-1",
        "points": 90
    },
    {
        "period": "2023-2",
        "points": 90
    }
]
```

### Example Curl Request
```
curl --location --request GET 'http://localhost:8080/api/users/1/points/calculateMontlyPoints?startDate=2023-01-01&endDate=2023-07-30' \
--header 'Authorization: Bearer <token>' \
--data-raw ''

```
