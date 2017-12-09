package main

import (
	"log"
	"net/http"
	"time"

	"github.com/boltdb/bolt"
)

const dbName = "people.db"

var bucketName = []byte("People")
var db *bolt.DB

func main() {
	openDB()

	http.HandleFunc("/people", handler)
	// http.HandleFunc("/people/:id", idHandler)
	http.ListenAndServe(":8000", nil)
}

func openDB() {
	var err error
	db, err = bolt.Open(dbName, 0644, &bolt.Options{Timeout: 1 * time.Second})
	if err != nil {
		log.Fatal(err)
	}
	// defer db.Close()

	db.Update(func(tx *bolt.Tx) error {
		_, err := tx.CreateBucketIfNotExists(bucketName)

		if err != nil {
			log.Fatal(err)
		}
		return err
	})
}
