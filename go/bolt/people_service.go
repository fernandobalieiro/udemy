package main

import (
	"encoding/json"
	"fmt"
	"log"

	"github.com/boltdb/bolt"
)

func save(p *Person) Person {
	db.Update(func(tx *bolt.Tx) error {
		bucket, err := tx.CreateBucketIfNotExists(bucketName)

		id, _ := bucket.NextSequence()
		p.ID = int(id)
		json, _ := json.Marshal(p)

		err = bucket.Put(convertIntToByteSlice(p.ID), json)

		if err != nil {
			log.Fatal(err)
		}

		return nil
	})

	return *p
}

func getPerson(ID int) Person {
	var p Person
	db.View(func(tx *bolt.Tx) error {
		bucket := tx.Bucket(bucketName)

		v := bucket.Get(convertIntToByteSlice(ID))

		json.Unmarshal(v, &p)
		fmt.Println("Person is: ", p)
		return nil
	})
	return p
}

func getPeople() []Person {
	var people []Person

	db.View(func(tx *bolt.Tx) error {
		bucket := tx.Bucket(bucketName)
		c := bucket.Cursor()

		for k, v := c.First(); k != nil; k, v = c.Next() {
			var p Person
			json.Unmarshal(v, &p)
			people = append(people, p)
			fmt.Printf("key=%s, value=%s\n", k, v)
		}

		return nil
	})
	return people
}
