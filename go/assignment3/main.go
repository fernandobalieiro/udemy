package main

import (
	"io"
	"os"
)

func main() {
	filename := os.Args[1]
	readFile(filename)
}

func readFile(filename string) {
	file, _ := os.Open(filename)
	io.Copy(os.Stdout, file)
}
