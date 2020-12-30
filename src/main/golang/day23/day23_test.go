package day23

import "testing"

func BenchmarkPart2(b *testing.B) {
	for i := 0; i < b.N; i++ {
		Part2("389125467",10_000_000)
	}
}


// Maps
// 10_000
// BenchmarkPart2-12    	       3	 463691191 ns/op

// Arrays
// 10_000
// BenchmarkPart2-12    	       4	 267155100 ns/op

// BenchmarkPart2-12    	       1	2235502688 ns/op