BUILD
=====
mvn dependency:resolve
mvn clean compile package
mvn test
mvn jacoco:report

CONTAINERIZATION
================
docker images
docker rmi storm-streaming-java:1.0
docker build -t storm-streaming-java:1.0 .

docker stop storm-streaming_java
docker rm storm-streaming_java
docker run --rm --name=storm-streaming_java storm-streaming-java:1.0