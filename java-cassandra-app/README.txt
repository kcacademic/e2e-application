BUILD
=====
mvn install

CONTAINERIZATION
================
docker images
docker rmi java-cassandra:1.0
docker build -t java-cassandra:1.0 .

docker stop java_cassandra
docker rm java_cassandra
docker run --rm --name=java_cassandra java-cassandra:1.0