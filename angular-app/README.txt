BUILD
=====
npm build
npm test
npm start
npm run e2e

CONTAINERIZATION
================
docker images
docker rmi angular-ngrx:1.0
docker build -t angular-ngrx:1.0 .

docker stop angular_ngrx
docker rm angular_ngrx
docker run --name=angular_ngrx -p 80:80 angular-ngrx:1.0