package main

import (
    "encoding/json"
    "log"
    "net/http"
    "github.com/gorilla/mux"
	"gopkg.in/mgo.v2"
)

type Word struct {
    Word string
    Count  int
}

func GetWords(w http.ResponseWriter, r *http.Request) {

	Host := []string{"127.0.0.1:27017"}
	session, err := mgo.DialWithInfo(&mgo.DialInfo{Addrs: Host})
	if err != nil {
		log.Fatal(err)
	}
	collection := session.DB("vocabulary").C("words")
	var words []Word
	collection.Find(nil).Sort("-count").Limit(25).All(&words)

	json.NewEncoder(w).Encode(words)
}

func main() {
    router := mux.NewRouter()
    router.HandleFunc("/api/words", GetWords).Methods("GET")
    log.Fatal(http.ListenAndServe(":8085", router))
}





