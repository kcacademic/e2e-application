BUILD
=====
npm test
npm start

CONTAINERIZATION
================
docker images
docker rmi node-mongo:1.0
docker build -t node-mongo:1.0 .

docker stop node_mongo
docker rm node_mongo
docker run --name=node_mongo -p 8085:8085 -e "MONGODB=mongodb://host.docker.internal/vocabulary" node-mongo:1.0