package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"strconv"
)

func handler(w http.ResponseWriter, r *http.Request) {

	if r.Method == "POST" {
		p := fromJSON(w, r)
		person := save(&p)

		json.NewEncoder(w).Encode(person)
		return
	}
	if r.Method == "GET" {

		var people []Person

		ids := r.URL.Query()["id"]

		if ids != nil {
			idInt, _ := strconv.Atoi(ids[0])
			p := getPerson(idInt)
			people = append(people, p)
		} else {
			people = getPeople()
		}

		fmt.Println(people)

		json.NewEncoder(w).Encode(people)
		return
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
