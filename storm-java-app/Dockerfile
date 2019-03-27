FROM storm:1.2.2

WORKDIR /opt/app
ARG JAR_FILE=target/storm-streaming-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar
COPY ${JAR_FILE} /opt/app/app.jar

ENTRYPOINT ["storm"]

CMD ["jar", "app.jar", "com.sapient.learning.WordDataStream"]
