BUILD
=====
gradle clean build
gradle bootRun

CONTAINERIZATION
================
docker images
docker rmi kotlin-kafka:1.0
docker build -t kotlin-kafka:1.0 .

docker stop kotlin_kafka
docker rm kotlin_kafka
docker run --rm --name=kotlin_kafka kotlin-kafka:1.0