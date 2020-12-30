package day23

import "fmt"

func Main(N int) {
	input := "389125467"
	input = "158937462"
	Part2(input,N)
}

func maxArray(l []T) T {
	ma := l[0]
	for _, e := range l {
		if e > ma {
			ma = e
		}
	}
	return ma
}

func contains(e T, l []T) bool {
	for _, i2 := range l {
		if e == i2 {
			return true
		}
	}
	return false
}

func minArray(l []T) T {
	ma := l[0]
	for _, e := range l {
		if e < ma {
			ma = e
		}
	}
	return ma
}

func buildListTest(input string) *LinkedList {

	last := &LinkedList{T(input[0] - '0'), nil}
	head := last

	for i, c := range input {
		if i > 0 {
			n := &LinkedList{T(c - '0'), nil}
			last.nextElement = n
			last = n
		}
	}


	return head
}

func BuildListPart2(input string) *LinkedList {

	last := &LinkedList{T(1), nil}
	head := last

	for i := 2; i <= 1_000_000; i++ {
		n := &LinkedList{T(i), nil}
		last.nextElement = n
		last = n
	}
	n := head

	for i, _ := range input {
		n.val = T(input[i]-'0')
		n = n.nextElement
	}
	return head
}

func Part2(input string,N int) {

	head := buildListTest(input)

	head = BuildListPart2(input)

	// lookup := make(map[T]*LinkedList,0)
	length := head.Length()
	lookup := make([]*LinkedList,length+1,length+1)

	h := head
	for h.HasNext(){
		lookup[h.val] = h
		h = h.nextElement
	}
	lookup[h.val] = h

	//fmt.Println("lookup",lookup)

	last := head

	fmt.Println(head.subList(0,20))

	ma := maxArray(head.toArray())

	for i := 0; i < N; i++ {

		if i%1_000_000 == 0 {
			fmt.Printf("ROUND %d \n", i)
		}

		PickedUp := head.subList(1, 4)

		dst := head.val - 1

		if dst == 0 {
			dst = ma
		}

		for contains(dst, PickedUp) {
			dst--
			if dst == 0 {
				dst = ma
			}
		}

		dstNode := lookup[dst]

		begLast := dstNode.nextElement

		begMove := head.At(1)
		endMove := head.At(3)
		begSlim := head.At(4)

		newHead := begSlim

		endMove.nextElement = nil

		dstNode.nextElement = begMove
		endMove.nextElement = begLast

		// Move the first element to the end, delete its pointer
		last = last.Last()
		last.nextElement = head
		head.nextElement = nil

		// Re-assign the head to the new Head
		head = newHead

	}

	n := lookup[T(1)].nextElement
	fmt.Println(n.val,n.nextElement.val)

	//fmt.Println(head.toArray())

	//newHead.insertAfter(dst,beg_of_move,lookup)

}
