# Random beer application

An application that generates details about a random beer.

## Checking out the repository
```
git clone https://iluci92@bitbucket.org/iluci92/randombeerapp.git
```

## Running the application
### Using the .jar file
```
java -jar build/libs/randomBeerApp-1.0-SNAPSHOT.jar
```

* The application will then be available at: http://localhost:8080/
* The API can be viewed using: http://localhost:8080/api
* To view the H2 data base, browse: http://localhost:8080/h2-console
* The data base will get initialized with the scripts in: `src/main/resources/data.sql`

### Running the unit/integration tests 
```
gradlew test
```

### Assembling a new .jar file

```
gradlew assemble
```

### To run webpack in development mode
```
npm install
npm run devServer
```

## Built With
* Gradle
* NPM
* Webpack
* Babel
* Spring boot: 
    * Spring Web
    * Spring Actuators
    * Spring Data
    * Spring Data REST
* H2
* React
* Axios

## Authors
* **Lucian Gabriel Ilie**
