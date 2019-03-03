BUILD
=====
mvn install

CONTAINERIZATION
================
docker images
docker rmi java-kafka:1.0
docker build -t java-kafka:1.0 .

docker stop java_kafka
docker rm java_kafka
docker run --rm --name=java_kafka java-kafka:1.0