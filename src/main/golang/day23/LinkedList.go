package day23

import "errors"

type T int

type LinkedList struct {
	val         T
	nextElement *LinkedList
}


func (l *LinkedList) next() (*LinkedList, error) {
	if l.nextElement == nil {
		return nil, errors.New("No next element")
	} else {
		return l.nextElement, nil
	}
}

func (l *LinkedList) At(index int) *LinkedList {
	t := l
	for i := 0; i < index; i++ {
		t = t.nextElement
	}
	return t
}

func (l *LinkedList) HasNext() bool {
	return l.nextElement != nil
}

func (l *LinkedList) Length() int {
	e := l
	cnt := 1
	for e.HasNext() {
		cnt++
		e = e.nextElement
	}
	return cnt
}

func (l *LinkedList) toArray() []T {
	arr := make([]T, 0)
	for l.HasNext() {
		arr = append(arr, l.val)
		l, _ = l.next()
	}
	arr = append(arr, l.val)
	return arr
}

func (l *LinkedList) Index(t T) (int, *LinkedList) {
	i := 0
	l1 := l
	for l1.val != t {
		l1 = l1.nextElement
		i++
	}
	return i, l1
}

func (l *LinkedList) Last() *LinkedList {
	l1 := l
	for l1.HasNext() {
		l1 = l1.nextElement
	}
	return l1
}
func (l *LinkedList) subList(f, to int) []T {
	arr := make([]T, 0)
	i := 0
	for l.HasNext() && i < f {
		l, _ = l.next()
		i++
	}

	for l.HasNext() && i < to {
		arr = append(arr, l.val)
		l, _ = l.next()
		i++
	}
	return arr
}
func (l *LinkedList) remove(a T) {
	p := l
	for p.val != a {
		p = p.nextElement
	}
	if p.nextElement != nil {
		*p = *p.nextElement
	} else {
		p.nextElement = nil
	}
}

func (l *LinkedList) remove3(a T) {
	curr := l
	var past *LinkedList
	for curr.val != a {
		curr, past = curr.nextElement, curr
	}
	if past != nil {
		past.nextElement = curr.nextElement
	} else {
		*l = *curr.nextElement
	}
}

func New(e ...T) *LinkedList {
	lastEl := &LinkedList{e[0], nil}
	head := lastEl
	for _, el := range e[1:] {
		current := &LinkedList{el, nil}
		lastEl.nextElement = current
		lastEl = current
	}
	return head
}

func (l *LinkedList) insert(at int, e T) error {
	if at == 0 {
		head := *l
		*l = LinkedList{e, &head}
		return nil
	}

	ls := l
	runOut := false
	for i := 0; i < at-1; i++ {
		if ls.HasNext() {
			ls = ls.nextElement
		} else {
			runOut = true
		}
	}

	if !runOut {
		ls.nextElement = &LinkedList{e, ls.nextElement}
		return nil
	}
	return errors.New("can't insert that far in")
}
