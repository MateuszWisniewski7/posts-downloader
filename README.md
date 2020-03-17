## Test
To run JUnit tests run command
````
gradlew clean test
````
or
````
./gradlew clean test
````

## Propeties
Properties needed to be set in application.yml
* api-url - correct get posts API url (if changed https://jsonplaceholder.typicode.com/posts)
* save-path - path to save posts

## Build
To build JAR run command
````
gradlew clean bootJar
````
or
````
./gradlew clean bootJar
````

## Run
To run application run command
````
gradlew clean bootRun
````
or
````
./gradlew clean bootRun
````
## Dependencies

* SpringBoot 2.2.5.RELEASE
* Java 1.8
* Lombok
