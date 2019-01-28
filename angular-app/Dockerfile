FROM node:alpine

RUN mkdir -p ../dockerized

WORKDIR ../dockerized

COPY package*.json ../dockerized/

RUN npm install

COPY . ../dockerized

EXPOSE 4200

CMD [ "npm", "start"]