package day8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    public static void main(String[] args) throws IOException {
        String fileName = "data/day8.txt";

        //day3.Input input = ;
        ArrayList<Task> tasks = new Input().readFile(fileName);

        //solvePart1(tasks);
        //solvePart2(rules);

        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).flip();
            if (i >= 1) {
                tasks.get(i - 1).flip(); // unflip
            }
            if(finishes(tasks)){
                System.out.printf("%d,finishes= %s \n", i, finishes(tasks));
                //solvePart1(tasks);
            }
        }


    }


    public static void solvePart1(ArrayList<Task> tasks) {

        int acc = 0;

        // kep
        HashSet<Integer> visited = new HashSet<Integer>();
        int i = 0;
        while (!visited.contains(i)) {
            visited.add(i);
            Task task = tasks.get(i);
            System.out.printf("visiting %d, task = %s \n", i, task.toString());
            switch (task.op) {
                case "acc" -> {
                    acc += task.q;
                    i += 1;
                }
                case "jmp" -> i += task.q;
                case "nop" -> i++;
            }

        }
        System.out.println(acc);
    }

    public static boolean finishes(ArrayList<Task> tasks) {

        HashSet<Integer> visited = new HashSet<Integer>();
        int i = 0;
        int acc = 0;
        while (!visited.contains(i)) {
            // only way to exit is to jump away
            if (i >= tasks.size()) {
                System.out.print("acc=");
                System.out.println(acc);
                return true;
            }

            visited.add(i);

            Task task = tasks.get(i);
            //System.out.printf("visiting %d, task = %s \n", i, task.toString());
            switch (task.op) {
                case "acc":
                    i += 1;
                    acc += task.q;
                    break;
                case "jmp":
                    i += task.q;
                    break;
                case "nop":
                    i++;
                    break;
            }
        }
        return false;
    }


}
