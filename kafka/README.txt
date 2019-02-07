Pull Image
==========
docker pull spotify/kafka

Run Image
=========
docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=0.0.0.0 --env ADVERTISED_PORT=9092 spotify/kafka