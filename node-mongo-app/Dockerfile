FROM node:alpine

RUN mkdir -p ../dockerized

WORKDIR ../dockerized

COPY package*.json ../dockerized/

RUN npm install

COPY . ../dockerized

EXPOSE 8085

CMD [ "npm", "start" ]