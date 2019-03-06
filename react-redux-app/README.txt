BUILD
=====
npm build
npm test
npm start
npm run e2e

CONTAINERIZATION
================
docker images
docker rmi react-redux:1.0
docker build -t react-redux:1.0 .

docker stop react_redux
docker rm react_redux
docker run --name=react_redux -p 80:80 react-redux:1.0