
# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 11, try this
FROM maven:3.8.6-openjdk-11

EXPOSE 8081

RUN mvn clean

RUN mvn install

CMD mvn spring-boot:run
