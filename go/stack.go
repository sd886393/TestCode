package main

import (
	"fmt"
)

const (
	a = 2
	b = iota
	c = iota - 1
	d = iota
)

const (
	e = 2
	f = iota
)

func main() {
	x := 10
	var y int32 = 20
	z := x + int(y)
	fmt.Println(x, y, z)
}
