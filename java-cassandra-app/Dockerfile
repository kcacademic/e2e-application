FROM openjdk:8-jdk-alpine

RUN apk add --update \
    bash \
    curl \
    py-pip
RUN bash -c "python <(curl https://bootstrap.pypa.io/get-pip.py)"; \
    pip install cqlsh

WORKDIR /opt/app
ARG JAR_FILE=target/java-cassandra-app-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /opt/app/app.jar
COPY wait_for_it.sh /opt/app

ENTRYPOINT ["sh", "wait_for_it.sh"]
CMD ["localhost", "9042", "java -jar app.jar"]