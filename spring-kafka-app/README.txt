BUILD
=====
mvn install

CONTAINERIZATION
================
docker images
docker rmi spring-kafka:1.0
docker build -t spring-kafka:1.0 .

docker stop spring_kafka
docker rm spring_kafka
docker run --rm --name=spring_kafka spring-kafka:1.0