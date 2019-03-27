BUILD
=====
go get github.com/gorilla/mux
go get gopkg.in/mgo.v2


CONTAINERIZATION
================
docker images
docker rmi go-mongo:1.0
docker build -t go-mongo:1.0 .

docker stop go_mongo
docker rm go_mongo
docker run --name=go_mongo -p 8085:8085 go-mongo:1.0