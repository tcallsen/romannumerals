# build project inside container
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

# run compiled jar from previous stage
FROM openjdk:11-jre-slim
COPY --from=build /usr/src/app/target/romannumerals-0.0.1-SNAPSHOT.jar /usr/app/romannumerals.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/romannumerals.jar"]