BUILD
=====
gradle clean build
gradle bootRun

CONTAINERIZATION
================
docker images
docker rmi kotlin-cassandra:1.0
docker build -t kotlin-cassandra:1.0 .

docker stop kotlin_cassandra
docker rm kotlin_cassandra
docker run --rm --name=kotlin_cassandra kotlin-cassandra:1.0