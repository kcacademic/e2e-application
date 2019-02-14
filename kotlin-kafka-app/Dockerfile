FROM openjdk:8-jdk-alpine

WORKDIR /opt/app
ARG JAR_FILE=build/libs/kotlin-kafka-app-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /opt/app/app.jar

ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]