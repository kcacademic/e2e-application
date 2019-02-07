BUILD
=====
mvn install

CONTAINERIZATION
================
docker images
docker rmi spring-cassandra:1.0
docker build -t spring-cassandra:1.0 .

docker stop spring_cassandra
docker rm spring_cassandra
docker run --rm --name=spring_cassandra spring-cassandra:1.0