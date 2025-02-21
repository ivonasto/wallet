## 1. Introduction 
This is a rudimentary wallet REST app, which supports the following features:
   - user creation 
   - user login and token exchange {basic auth}
   - wallet creation {JWT auth}
   - deposit / withdrawal of funds {JWT auth}
   - viewing of wallet balance {JWT auth} 

#### Currencies
The app supports three currencies [EUR,BGN,USD].
When making transactions, funds are converted at flat rates if the currency differs from the target wallet’s.

#### IBAN
Each wallet has an associated IBAN, which follows the [ISO 7064 standard](#IBAN validation). 
The structure of an IBAN in the app is as follows: "WT" + "mod 97 checksum" + "UUID.substring(0,16)" 


#### IBAN validation
The MOD 97-10 checksum algorithm (from ISO 7064) is commonly used for IBAN validation. It ensures that a number, when divided by 97, leaves a remainder of 1.

In this app it is used to validate and create valid IBANs. 

It works in the following way:

Given an IBAN, first four characters (country code + checksum) are moved to the end.
Then the letters are converted to numbers (A = 10, B =11 ..., Z =35). The whole resulting number is divided by 97 and if the IBAN is valid the remainder should be 1. 

In the real world other checks follow, like validity of bank code etc. 


## 2. Deployment
#### 2.1. Database

A) via docker (preferred)
- Install Docker, e.g. [docker desktop](https://www.docker.com/products/docker-desktop/)
- Run the database via docker compose - `docker compose up` in the root directory of the project

B) local DB setup
- Have MariaDB running locally
- With user - root and password - root
- run the provided [init.sql](init.sql) file in order to create the tables

#### 2.1. Application

A) via gradle directly
- built the application via gradle: `./gradlew clean build`
- serve the application via gradle: `./gradlew bootRun`

B) via IntelliJ 
- open project as gradle project
- run by starting `WalletApplication`
   

## 3. API overview 

The full API documentation is available through the Swagger UI, once the app is running:

[Swagger UI](http://localhost:8080/swagger-ui/index.html)

#### Start here 

The API uses JWT for authentication. In order to access the endpoints, the following steps must be followed.

1. Create user at  `POST api/v1/users` 
2. Generate a token using the `POST /token` and user details created at step 1 with `Authorization: Basic <base64_encoded_username:password>`
3. Access the rest of the app with the token in `Authorization: Bearer <token>`

Postman examples for this are provided in the following collection - [postman_collection.json](Postman_collection.json). 

Start from the "Initialize postman vars" folder. It does the following:
- create two users 
- create token for each user and store it in a Postman variable
- create a wallet for each and store its IBAN in a Postman variable

To go through the app functionalities, use the "Testing-run in sequence" folder inside the Postman collection.
(Run the requests from top to bottom)

## 4. Technologies used

The following technologies were used: 

### App

- Java 17 
- Spring Boot 
- Spring Security and its JWT implementation
- Gradle
- Docker
- MariaDB
- Swagger

### Tests
- JUnit
- Postman


## 5. Limitations
### Technological limitations
1. IBAN creation is tiny bit more collision prone, due to UUID pruning. 
2. Passwords are not encoded, nor have proper rules for generation.
3. Limited operation types (missing delete wallet, update user password etc.)

### Feature limitations 
1. The app does not require proof of withdrawal. For a more realistic approach transactions can have status `pending`, until approved by the wallet holder.