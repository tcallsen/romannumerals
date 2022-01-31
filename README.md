# Roman Numerals Conversion REST API

Java-based REST API that converts decimal numbers to Roman Numerals. The API supports converting decimals from `1-3999` passed as a the `query` query parameter.

Example request:

```
GET http://localhost:8080/romannumeral?query=255
```

Example response:

```
Content-Type: application/json

{
  input: "255",
  output: "CCLV"
}
```

## Roman Numerals Specification

This API adheres to Roman Numerals Standard Form as [defined on Wikipedia](https://en.wikipedia.org/wiki/Roman_numerals#Standard_form), including the use of "subtractive notation" (e.g. "IV" representing 4).

## Dependencies

[Spring Initializr](https://start.spring.io/) was used to bootstrap the project, with the following MVC-like code structure: 

- Controllers are used to validate request parameters and tie into Services
- A Service performs the Roman Numerals conversion
- Models define the data structures used to generate JSON output form a conversion or request validation error

The following dependencies are required to build the project:

- Java JDK 11
- Maven 3

The following optional dependencies may be necessary:

- Docker - only required if building/running service as a Docker container

## Build and Run

Execute the following command in the root of the repository to build and run the REST API:

```
mvn spring-boot:run
```

## Testing

### Unit Tests

Unit tests were created using JUnit and [Spring's MockMvc](https://spring.io/guides/gs/testing-web/), which provides mocks for the servlet and web layer. Tests can be executed by running the following command:

```
mvn test
```

### Integration Tests

More information soon.

## Operational Monitoring and Metrics

[Spring Actuator](https://github.com/spring-projects/spring-boot/tree/v2.6.3/spring-boot-project/spring-boot-actuator) has been enabled to support monitoring, metric scraping, and access to logs. The actuator is available anytime the REST API is running.

Useful endpoints include:

- Health check: `http://localhost:8080/actuator/health`
- Application log: `http://localhost:8080/actuator/logfile`
- Metrics: `http://localhost:8080/actuator/metrics`
- All enabled endponts: `http://localhost:8080/actuator`

### Logging

Application log information is written to a file at `./logs/app.log`, and can be accessed via the Actuator at `http://localhost:8080/actuator/logfile`. The base log level is set to `INFO`, with Spring web information set to `DEBUG`.

## Docker Container

There are two different methods to run this REST API inside of a Docker container detailed below.

### Option 1: Build Java Project with host OS

This command will use Java and Maven dependencies on the host OS to compile and build the Maven project:

```
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=tcallsen/romannumerals:0.0.1-SNAPSHOT
```

### Option 2: Build Java Project inside Docker Containers

This command will compile the Java Project inside a Docker container. This is useful if the host OS does not have Java or Maven installed:

```
docker build -t tcallsen/romannumerals:0.0.1-SNAPSHOT .
```

### Run Docker Container

Once the container is built, execute the following command to run the container and expose the REST API locally on port 8080:

```
docker run --rm -it -p 8080:8080 tcallsen/romannumerals:0.0.1-SNAPSHOT
```