package day16;


import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Solution {

    public static void main(String[] args) throws IOException {
        String fileName = "data/day16.txt";

        Triplet<List<List<Rule>>, List<Integer>, List<List<Integer>>> input = new Input().readFile(fileName);

        System.out.println(input.getValue0());
        System.out.println(input.getValue1());
        System.out.println(input.getValue2());

        List<List<Integer>> validTickets = solvePart1(input.getValue0(), input.getValue2());
        System.out.println(validTickets);
        solvePart2(input.getValue0(), input.getValue1(), validTickets);
    }

    public static boolean isValid(List<Rule> rules, Integer i) {
        return rules.stream().anyMatch(rule -> rule.valid(i));
    }


    public static List<List<Integer>> solvePart1(List<List<Rule>> rules, List<List<Integer>> tickets) {
        List<Integer> invalid = new ArrayList<>();
        List<List<Integer>> validTickets = new ArrayList<>(); // valid tickets

        for (int i = 0; i < tickets.size(); i++) {
            List<Integer> ticket = tickets.get(i);

            boolean validTicket = true;

            // Iterate over rules
            for (Integer field : ticket) {
                boolean valid = false;
                for (int i1 = 0; i1 < rules.size(); i1++) {
                    if (isValid(rules.get(i1), field)) {
                        valid = true;
                    }
                }
                if (!valid) {
                    invalid.add(field);
                    validTicket = false;
                }
            }
            // if is all the fields are valid
            if (validTicket) {
                validTickets.add(ticket);
            }
        }
        //System.out.println(invalid);
        if (invalid.size() > 1) {
            System.out.println(invalid.stream().reduce(Integer::sum).get());
        }
        return validTickets;

    }


    // Find which rule applies to which columns
    // return an List of ColID (index = rule)
    public static int[] findCombination(List<List<Integer>> validRules) {

        // RuleID = index
        // col = value
        int[] combination = new int[validRules.size()];

        // (RuleID,ColID)
        HashMap<Integer, List<Integer>> runner = new HashMap();

        for (int i = 0; i < validRules.size(); i++) {
            runner.put(i, validRules.get(i));
        }

        while (runner.size() > 0) {
            for (Integer ruleID : runner.keySet()) {
                if (runner.get(ruleID).size() == 1) {
                    int colID = runner.get(ruleID).get(0);
                    combination[ruleID] = colID;

                    // The column has been filled.
                    // Delete from all of them
                    for (Integer ruleID2 : runner.keySet()) {
                        runner.get(ruleID2).removeIf(x -> (int) x == colID);
                    }
                    // Remove from hashmap
                    runner.remove(ruleID);
                    break;
                }
            }
        }
        return combination;
    }


    public static void solvePart2(List<List<Rule>> rules, List<Integer> myTicket, List<List<Integer>> tickets) {


        // List of rules valid for each column of the ticket
        List<List<Integer>> validRules = new ArrayList<>();
        for (List<Rule> rule : rules) {
            validRules.add(new ArrayList<>());
        }


        int ruleID = 0;
        for (List<Rule> rule : rules) {
            System.out.printf("doing rule %s\n", rule.toString());
            for (int colID = 0; colID < tickets.get(0).size(); colID++) {
                final int finalColID = colID;
                List<Integer> entries = tickets.stream().map(x -> x.get(finalColID)).collect(Collectors.toList());
                System.out.printf("entries %s\n", entries.toString());
                boolean valid = true;
                for (Integer entry : entries) {
                    if (!isValid(rule, entry)) {
                        System.out.printf("entry %d broke rule %s\n", entry, rule.toString());
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    validRules.get(ruleID).add(colID);
                }
            }
            ruleID++;
        }
        System.out.println("HERE");
        validRules.forEach(x -> System.out.println(x.toString()));


        int[] sol = findCombination(validRules);
        long acc = 1;
        if (myTicket.size() > 5) {
            for (int i = 0; i < 6; i++) {
                acc *= myTicket.get(sol[i]);
            }
            System.out.printf("sol=%d\n", acc);
        }


        System.out.println(Arrays.toString(sol));
    }


}



