package main

import (
	"strconv"
)

// Person type
type Person struct {
	ID   int    `json:"id"`
	Name string `json:"name"`
	Age  int    `json:"age"`
}

func convertIntToByteSlice(value int) []byte {
	return []byte(strconv.Itoa(value))
}
