FROM openjdk:8-alpine

RUN apk --update add git curl tar bash ncurses && \
    rm -rf /var/lib/apt/lists/* && \
    rm /var/cache/apk/*

ARG SPARK_VERSION=2.4.0
ARG SPARK_HOME=/usr/local/spark-$SPARK_VERSION-bin-hadoop2.7
RUN curl -sL "http://www-us.apache.org/dist/spark/spark-$SPARK_VERSION/spark-$SPARK_VERSION-bin-hadoop2.7.tgz" | tar -xz -C /usr/local

ENV PATH $PATH:$SPARK_HOME/bin
ENV SPARK_MASTER local[*]

WORKDIR /opt/app
ARG JAR_FILE=target/scala-2.11/spark-streaming-scala-assembly-1.0.jar
COPY ${JAR_FILE} /opt/app/app.jar

CMD spark-submit --class com.sapient.learning.WordDataStream --master local app.jar