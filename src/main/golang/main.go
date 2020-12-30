package main

import (
	"awesomeProject/day21"

	//"awesomeProject/day23"
	"flag"
	"log"
	"os"
	"runtime/pprof"

	//"awesomeProject/day10"
	"fmt"
)


func main() {
	fmt.Println("Hello, playground")

	var cpuprofile = flag.String("cpuprofile", "", "write cpu profile to file")

	//var N = flag.Int("N", 10, "number of loops")

	flag.Parse()
	if *cpuprofile != "" {
		f, err := os.Create(*cpuprofile)
		if err != nil {
			log.Fatal(err)
		}
		pprof.StartCPUProfile(f)
		defer pprof.StopCPUProfile()
	}

	//day23.Main(*N);

	day21.Main();


}
