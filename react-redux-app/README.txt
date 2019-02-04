BUILD
=====
npm build

CONTAINERIZATION
================
docker images
docker rmi react-redux:1.0
docker build -t react-redux:1.0 .

docker stop react_redux
docker rm react_redux
docker run --name=react_redux -p 8080:8080 react-redux:1.0