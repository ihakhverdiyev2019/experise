
# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 11, try this
FROM openjdk:11

WORKDIR /experise
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run
