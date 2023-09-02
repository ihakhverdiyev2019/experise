
# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 11, try this
FROM openjdk:11

WORKDIR /app
COPY . .
EXPOSE 8081

RUN mvn clean install

CMD mvn spring-boot:run
