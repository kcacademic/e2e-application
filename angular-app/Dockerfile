FROM node:alpine

COPY package*.json /tmp/
RUN cd /tmp && npm install
RUN mkdir -p /opt/app && cp -a /tmp/node_modules /opt/app/
WORKDIR /opt/app
ADD . /opt/app

EXPOSE 4200
CMD [ "npm", "start"]