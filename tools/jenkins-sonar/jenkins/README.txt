CONTAINERIZATION
================
docker images
docker rmi jenkins:1.0
docker build -t jenkins:1.0 .

docker stop jenkins
docker rm jenkins
docker run --rm --name=jenkins -p 8880:8080 -p 50000:50000 jenkins:1.0
