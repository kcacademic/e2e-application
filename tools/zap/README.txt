Pull Image
==========
docker pull owasp/zap2docker-stable

Run Image
=========
docker run -u zap -p 8080:8080 -p 8090:8090 -i owasp/zap2docker-stable zap-webswing.sh
docker run -u zap -p 8080:8080 -i owasp/zap2docker-stable zap.sh -daemon -host 0.0.0.0 -port 8080

Docker Compose
==============
docker-compose up