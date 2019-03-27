BUILD
=====

CONTAINERIZATION
================
docker images
docker rmi storm-clojure:1.0
docker build -t storm-clojure:1.0 .

docker stop storm-clojure
docker rm storm-clojure
docker run --rm --name=storm-clojure storm-clojure:1.0

STORM SUBMIT
============
 