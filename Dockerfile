#!/bin/bash

[ -z "$JAVA_XMS" ] && JAVA_XMS=512m
[ -z "$JAVA_XMX" ] && JAVA_XMX=512m


FROM maven:3.8.5-openjdk-11 as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=build ./build/target/*.jar ./experise-.jar
ENTRYPOINT [java
           -Dapplication.name=experise-app \
           -Dotel.metrics.exporter=none \
           -Dotel.traces.exporter=otlp \
           -Dotel.resource.attributes=service.name=experise-app \
           -Dotel.exporter.otlp.traces.endpoint=http://38.242.150.128:4317 \
           -Dotel.service.name=experise-app \
           -Dotel.javaagent.debug=false \
           -javaagent:../agents/opentelemetry-javaagent.jar"
           -jar experise-.jar
            ]

