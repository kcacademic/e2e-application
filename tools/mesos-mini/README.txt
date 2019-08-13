docker run --rm --privileged -p 5050:5050 -p 5051:5051 -p 8080:8080 mesos/mesos-mini

docker exec <container_id> /bin/bash
docker exec <container_id> docker ps