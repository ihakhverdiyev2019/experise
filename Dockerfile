#!/bin/bash
FROM curlimages/curl:7.81.0 AS download
RUN curl --silent --fail -L "https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar" \
    -o "$HOME/opentelemetry-javaagent.jar"

FROM maven:3.8.5-openjdk-11 as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=build ./build/target/*.jar ./experise-.jar
COPY --from=download /home/curl_user/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

ENTRYPOINT ["java", \
           "-Dapplication.name=experise-app", \
           "-Dotel.metrics.exporter=none", \
           "-Dotel.traces.exporter=otlp", \
           "-Dotel.resource.attributes=service.name=experise-app", \
           "-Dotel.exporter.otlp.traces.endpoint=http://38.242.150.128:4317", \
           "-Dotel.service.name=experise-app", \
           "-Dotel.javaagent.debug=false", \
           "-javaagent:/opentelemetry-javaagent.jar", \
           "-jar", "experise-.jar"]

