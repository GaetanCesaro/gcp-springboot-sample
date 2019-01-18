# GCP Spring Boot sample

A Spring Boot application sample on top of Google Cloud Platform App Engine.

## Localhost run

Don't forget to add this VM argument if your 8080 port is already used :

    -Dserver.port=8001
    
Then run with :

    ./mvnw -DskipTests spring-boot:run

## Deploy

To deploy the application to Google App Engine (current application), run :

    ./mvnw -DskipTests appengine:deploy
  