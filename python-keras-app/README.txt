BUILD
=====
<TBD>

CONTAINERIZATION
================
docker images
docker rmi python-keras:1.0
docker build -t python-keras:1.0 .

docker stop python-keras
docker rm python-keras
docker run --name=python_keras -p 5000:5000 python-keras:1.0