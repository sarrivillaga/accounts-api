# Accounts REST API
This project has the implementation of the Accounts REST API.

Here is the list of endpoints implemented:

    -/api/v1/accounts
    -/api/v1/accounts/{accountNumber}
    -/api/v1/accounts/{accountNumber}/transactions
    
It was build using Gradle, Springboot 2.3.5, Spring JPA and Lombok.

Additionally it uses h2database to run an in memory database.

### Build the app

Open your terminal and go to the root folder of this project and execute:


  `./gradlew build`


### Execute the APP

`java -jar build/libs/accounts-0.0.1-SNAPSHOT.jar `

