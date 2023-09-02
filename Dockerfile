FROM openjdk:11
VOLUME /tmp
ADD target/experise-0.0.1-SNAPSHOT.jar experise-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","experise-0.0.1-SNAPSHOT.jar"]