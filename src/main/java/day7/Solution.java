package day7;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    public static void main(String[] args) throws IOException {
        String fileName = "data/day7.txt";

        //day3.Input input = ;
        ArrayList<Rules> rules = new day7.Input().readFile(fileName);

        solvePart1(rules);
        solvePart2(rules);


    }


    public static void solvePart1(ArrayList<Rules> rules) {
        HashMap<String, ArrayList<Integer>> reverseMap = new HashMap<>();
        HashSet<Integer> toDos = new HashSet<>();
        HashSet<Integer> alreadyValidated = new HashSet<>();

        int i = 0;
        for (Rules rule : rules) {
            if (rule.Color.equals("shiny gold")) {
                alreadyValidated.add(i);
                toDos.add(i);
            }

            for (Rule contain : rule.Contains) {
                if (reverseMap.containsKey(contain.Color)) {
                    reverseMap.get(contain.Color).add(i);
                } else {
                    ArrayList<Integer> al = new ArrayList<>();
                    al.add(i);
                    reverseMap.put(contain.Color, al);
                }
            }
            i++;
        }


        /// Now iterate other colors
        while (!toDos.isEmpty()) {
            Integer bagID = toDos.iterator().next();
            String color = rules.get(bagID).Color; // color of the BagID

            // This BagID is part of these bags:
            ArrayList<Integer> parentBags = reverseMap.getOrDefault(color, new ArrayList<Integer>());

            for (Integer parentBag : parentBags) {
                if (!alreadyValidated.contains(parentBags)) {
                    toDos.add(parentBag);
                } else {
                    continue;
                }
            }
            alreadyValidated.add(bagID);
            toDos.remove(bagID);
        }

        System.out.println(alreadyValidated);
        System.out.println(alreadyValidated.size() - 1);

    }


    public static void solvePart2(ArrayList<Rules> rules) {
        HashSet<Task> toDos = new HashSet<>();
        HashMap<String, Integer> IDLookup = new HashMap<>();

        int i = 0;
        for (Rules rule : rules) {
            IDLookup.put(rule.Color, i);
            if (rule.Color == "mirrored crimson") {
                System.out.println(rule);
            }
            i++;
        }
        toDos.add(new Task(IDLookup.get("shiny gold"), 1));

        int cnt = 0;
        //
        while (!toDos.isEmpty()) {
            Task task = toDos.iterator().next();
            toDos.remove(task);
            Rules parent = rules.get(task.getBagID());

            for (Rule child : parent.Contains) {
                if (IDLookup.containsKey(child.Color)) {
                    Integer childID = IDLookup.get(child.Color);
                    Task newTask = new Task(childID, task.getCnt() * child.cnt);
                    cnt += task.getCnt() * child.cnt;
                    toDos.add(newTask);
                }
            }
        }
        System.out.println(cnt);
    }
}

class Task {
    int bagID;
    int cnt;

    public Task(int bagID, int cnt) {
        this.cnt = cnt;
        this.bagID = bagID;
    }

    public int getBagID() {
        return bagID;
    }

    public int getCnt() {
        return cnt;
    }
}
