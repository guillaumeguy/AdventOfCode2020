package day21

import (
	"fmt"
	"io/ioutil"
	"log"
	"math"
	"os"
	"sort"
	"strings"
)

type Allergen struct {
	name       string
	ingredient string
}



func intersection(ls1, ls2 []string) []string {
	current := make([]string, 0)
	exists := make(map[string]bool, len(ls1))

	for _, i2 := range ls1 {
		exists[i2] = true

	}

	for _, i2 := range ls2 {
		if _, ok := exists[i2]; ok {
			current = append(current, i2)
		}
	}
	return current
}

func cleanUp(s string) string {
	a := strings.ReplaceAll(strings.ReplaceAll(s, ":space:", ""), ")", "")
	return strings.TrimSpace(a)
}

func Main() {
	data := loadFile("./day21/data.txt")

	ingredientsCnt, suspects := Part1(data)
	sol := Part2(ingredientsCnt, suspects)

	acc := make([]*Allergen,0)
	for a, i := range sol {
		acc = append(acc,&Allergen{a,i})
	}
	//sort.Sort(sort.Reverse(sort.StringSlice(acc)))

	sort.SliceStable(acc, func(i, j int) bool {
		return acc[i].name < acc[j].name
	})


	solStr := strings.Builder{}
	for _, i2 := range acc {
		solStr.WriteString(i2.ingredient)
		solStr.WriteString(",")
	}
	fmt.Println(solStr.String())



}

func Part2(ingredientsAll map[string]int, suspects map[string][]string) map[string]string {

	var key string
	for k, _ := range suspects {
		key = k
		break
	}

	fmt.Println("STARTING WITH",key,"->",suspects[key],len(suspects[key]))

	for _, ingredientsIt := range suspects[key] {
		s := step(ingredientsIt, key, suspects, make(map[string]string, 0))
		if s != nil {
			fmt.Println("solution=", s)
			return s
		}
	}


	fmt.Println("no solution")
	return make(map[string]string, 0)
}

func step(ing, alle string, toGo map[string][]string, sol map[string]string) map[string]string {

	fmt.Println("NOW DOING",ing,alle)

	// Create new To Go List with removed elements
	newToGo := make(map[string][]string, len(toGo))

	// The allergent with the least ingredients possible
	var minAllergen string
	minCnt := math.MaxInt64

	for a, ings := range toGo {
		_,alreadyProcessed := sol[a]
		if a != alle && ! alreadyProcessed {
			filteredIngs := make([]string, 0)
			for _, i := range ings {
				if i != ing {
					filteredIngs = append(filteredIngs, i)
				}
			}
			newToGo[a] = filteredIngs

			// Every allergens needs at least 1 ingredients
			if len(filteredIngs) == 0 {
				return nil
			}

			// Find best allergen for next iteration
			if len(filteredIngs) < minCnt {
				minAllergen = a
				minCnt = len(filteredIngs)
			}
		}
	}

	// Success
	if len(newToGo) == 0 {
		sol[alle] = ing
		return sol
	}

	// safety
	if minAllergen == "" {
		return nil
	}

	// Update NewSol too (add element this time
	newSol := make(map[string]string, 0)
	for a, i := range sol {
		newSol[a] = i
	}
	newSol[alle] = ing

	fmt.Println("new allergen",minAllergen)

	for _, ingredientsIt := range newToGo[minAllergen] {
		s := step(ingredientsIt, minAllergen, newToGo, newSol)
		if s != nil {
			return s
		}
	}

	// Nothing worked ...
	return nil
}

func Part1(data []string) (map[string]int, map[string][]string) {

	allergens := make(map[string][][]string, 0)
	ingredientsCounter := make(map[string]int, 0)

	for _, line := range data {
		all := strings.Split(line, "(contains")
		ingredients := make([]string, 0)
		for _, ingredient := range strings.Split(all[0], " ") {
			i := cleanUp(ingredient)
			if len(i) > 0{
				ingredients = append(ingredients, i)
				ingredientsCounter[i] += 1
			}
		}

		for _, alleg := range strings.Split(all[1], ",") {
			a := cleanUp(alleg)
			allergen := ingredients
			if _, ok := allergens[a]; !ok {
				allergens[a] = [][]string{allergen}
			} else {
				allergens[a] = append(allergens[a], allergen)
			}
		}
	}

	suspects := make(map[string][]string, 0)
	suspectIngredients := make(map[string]bool, 0)
	for allergen, ingredients := range allergens {
		var s []string
		s = ingredients[0]
		for i, i2 := range ingredients {
			if i > 0 {
				s = intersection(s, i2)
			}
		}
		for _, e := range s {
			suspectIngredients[e] = true
		}

		suspects[allergen] = s

	}

	cnt := 0
	for s, c := range ingredientsCounter {
		if _, ok := suspectIngredients[s]; !ok {
			cnt += c
		}
	}
	fmt.Println(cnt)

	return ingredientsCounter, suspects

}

func loadFile(fileName string) []string {

	file, err := os.Open(fileName)

	defer func() {
		if err = file.Close(); err != nil {
			log.Fatal(err)
		}
	}()

	b, err := ioutil.ReadAll(file)

	return strings.Split(string(b), "\n")

}
