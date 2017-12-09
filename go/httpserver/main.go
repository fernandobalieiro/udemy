package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"os"
)

// Person type
type Person struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

func main() {
	http.HandleFunc("/", handler)
	http.ListenAndServe(":8000", nil)
}

func handler(w http.ResponseWriter, r *http.Request) {

	if r.Method == "POST" {
		p := fromJSON(w, r)
		people := appendToFile(p, w, r)

		json.NewEncoder(w).Encode(people)
	}
	if r.Method == "GET" {
		people := readFromFile()

		fmt.Println(people)

		json.NewEncoder(w).Encode(people)
	}
	http.Error(w, "Method not allowed.", 405)
}

func fromJSON(w http.ResponseWriter, r *http.Request) Person {
	var p Person

	if r.Body == nil {
		fmt.Println("Invalid Request Body.")
		http.Error(w, "Please send a request body", 400)
		return p
	}

	err := json.NewDecoder(r.Body).Decode(&p)

	if err != nil {
		fmt.Println("Error when decoding Json.")
		http.Error(w, err.Error(), 400)
		return p
	}
	return p
}

func appendToFile(p Person, w http.ResponseWriter, r *http.Request) []Person {
	people := readFromFile()

	people = append(people, p)

	res, _ := json.Marshal(people)

	ioutil.WriteFile("people", res, 0666)

	return people
}

func readFromFile() []Person {
	bs, err := ioutil.ReadFile("people")
	if err != nil {
		fmt.Println("Error: ", err)
		os.Exit(1)
	}

	fmt.Println(string(bs))

	var people []Person
	json.Unmarshal(bs, &people)
	return people
}
