package day10

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"sort"
	"strconv"
)

func readFile(fileName string) []int64{

	f, err := os.Open(fileName)

	if err != nil {
		log.Fatal(err)
	}

	defer f.Close()

	data := make([]int64,0)

	scanner := bufio.NewScanner(f)

	max := int64(0);
	for scanner.Scan() {
		d,_ := strconv.ParseInt(scanner.Text(),10,64)
		if d > max {
			max = d
		}
		data = append(data, d)
	}

	data = append(data,max +3 )

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}

	return data
}

func part1(s []int64) {
	s = append(s,0)
	sort.Slice(s, func(i, j int) bool {
		return s[i] < s[j]
	})

	fmt.Println(s)

	prev := s[0]
	acc := make(map[int64]int64,0)
	for i := 1; i < len(s); i++ {
		v := s[i] - prev
		fmt.Println(i,v)

		if c,ok := acc[v];ok {
			acc[v] = c+1
		}else{
			acc[v] = 1
		}
		prev = s[i]
	}
	fmt.Println(acc)
}


func Main(){
	fileName := "data/day10Test.txt"
	data := readFile(fileName)

	part1(data)
}