BUILD
=====
lein run
lein test
lein uberjar


CONTAINERIZATION
================
docker images
docker rmi storm-clojure:1.0
docker build -t storm-clojure:1.0 .

docker stop storm_clojure
docker rm storm_clojure
docker run --rm --name=storm_clojure storm-clojure:1.0

STORM SUBMIT
============
 