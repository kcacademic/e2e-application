BUILD
=====
mvn install

CONTAINERIZATION
================
docker images
docker rmi kafka-producer:1.0
docker build -t kafka-producer:1.0 .

docker stop kafka_producer
docker rm kafka_producer
docker run --name=kafka_producer kafka-producer:1.0