package main

import (
    "encoding/json"
    "log"
    "net/http"
    "github.com/gorilla/mux"
	"gopkg.in/mgo.v2"
	"os"
)

// Word Data structure for word
type Word struct {
    Word string
    Count  int
}

// GetWords Function to fetch words from MongoDB
func GetWords(w http.ResponseWriter, r *http.Request) {

	var hostString = os.Getenv("MONGODB")

	if len(hostString) == 0 {
		hostString="127.0.0.1:27017"
    }
	
	Host := []string{hostString}
	session, err := mgo.DialWithInfo(&mgo.DialInfo{Addrs: Host})
	if err != nil {
		log.Fatal(err)
	}
	collection := session.DB("vocabulary").C("words")
	var words []Word
	collection.Find(nil).Sort("-count").Limit(25).All(&words)

	json.NewEncoder(w).Encode(words)
}

// GetTest A test function
func GetTest(w http.ResponseWriter, r *http.Request) {
    json.NewEncoder(w).Encode("Hello, world.")
}

// Hello Another test function
func Hello() string {
    return "Hello, world."
}

// The main function
func main() {
    router := mux.NewRouter()
	router.HandleFunc("/api/words", GetWords).Methods("GET")
	router.HandleFunc("/", GetTest).Methods("GET")
    log.Fatal(http.ListenAndServe(":8085", router))
}
