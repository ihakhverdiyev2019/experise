
# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 11, try this
FROM openjdk:11

<<<<<<< HEAD
WORKDIR /experise
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run
=======
COPY src/ /src/
RUN javac /src/az/elixir/experise/ExperiseApplication.java -d /app

FROM openjdk:11
COPY --from=builder /app /app
WORKDIR /app
EXPOSE 8081

CMD ["java", "az.elixir.experise.ExperiseApplication"]
>>>>>>> parent of b0dee2b (updated)
