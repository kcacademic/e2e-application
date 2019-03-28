FROM golang:1.11

WORKDIR $GOPATH/src/words
COPY . .
RUN go get -d -v ./...
RUN go install -v ./...

EXPOSE 8085
CMD [ "words" ]