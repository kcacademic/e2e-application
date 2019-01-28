BUILD
=====
npm start

CONTAINERIZATION
================
docker images
docker rmi angular-ngrx:1.0
docker build -t angular-ngrx:1.0 .

docker stop angular_ngrx
docker rm angular_ngrx
docker run --name=angular_ngrx -p 4200:4200 angular-ngrx:1.0