BUILD
=====
SET GOPATH=C:\Users\kumchand0\Apps\e2e-application\go-mongo-app
cd src\words
dep init
dep ensure

go build words
go test words
go install words

CONTAINERIZATION
================
docker images
docker rmi go-mongo:1.0
docker build -t go-mongo:1.0 .

docker stop go_mongo
docker rm go_mongo
docker run --name=go_mongo -p 8085:8085 go-mongo:1.0