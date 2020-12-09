package day9;

import com.sun.source.doctree.SystemPropertyTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws IOException {
        String fileName = "data/day9.txt";

        //day3.Input input = ;
        ArrayList<Long> values = new Input().readFile(fileName);

        int lookback = 25;


        //solvePart1(values, lookback);
        solvePart2(values, lookback);

    }

    public static boolean twoSum(List<Long> ar, Long val) {
        HashSet<Long> tracker = new HashSet<>();

        for (int i = 0; i < ar.size(); i++) {
            tracker.add(val - ar.get(i));
        }

        for (int i = 0; i < ar.size(); i++) {
            if (tracker.contains(ar.get(i))) {
                return true;
            }
        }
        return false;

    }

    public static void solvePart1(ArrayList<Long> values, int lookback) {
        for (int i = lookback; i < values.size(); i++) {
            if (!twoSum(values.subList(i - lookback, i), values.get(i))) {
                System.out.println(i);
                System.out.println(values.get(i));
            }
        }
    }

    /*
    We progress by incrementing the Leftmost element
        and finding the sequence by incrementing the right-most element


    lastMove = 1 // if we removed right most element
    lastMove = 0 // if no removal
    lastMove = -1 // if we removal LEFT most element

     */
    public static SolOrCursor contiguousSequence(List<Long> values,
                                                 long target,
                                                 int from,
                                                 int to
    ) {

        List<Long> listInScope = values.subList(from, to);
        long currentSum = listInScope.stream().reduce(0L, Long::sum);
        System.out.printf("starting: from=%d,to=%d,currentSum=%d \n", from, to, currentSum);

        Sol s = null;

        if (currentSum == target) {
            s = new Sol(from, to);
            return new SolOrCursor(s, from, to);
        }

        int cursor = to;
        while (currentSum > target) {
            currentSum -= values.get(cursor);
            cursor--;
            System.out.printf("DECREMENT - from=%d,to=%d,currentSum=%d \n", from, cursor, currentSum);
        }

        while (currentSum <= target) {
            if (currentSum == target) {
                s = new Sol(from, to);
                return new SolOrCursor(s, from, to);
            }
            currentSum += values.get(cursor);
            cursor++;
            System.out.printf("INCREMENT - from=%d,to=%d,currentSum=%d \n", from, cursor, currentSum);
        }
        // No option found
        return new SolOrCursor(s, from, cursor);

    }

    public static void solvePart2(ArrayList<Long> values, int lookback) {


        int from = 0;
        int to = 2;
        long target = 14144619L;
        for (int i = 0; i < values.size(); i++) {
            SolOrCursor s = contiguousSequence(values, target, from, to);

            if (s.sol != null) {
                System.out.print("FINSIHED !");
                System.out.println(s.toString());
                System.out.printf("sol=%d", s.part2Sol(values));
                break;
            }

            if(values.get(s.from) >= target){
                System.out.print("INDIVIDUAL ELEMENT EXCEEDED TARGET!");
                break;
            }

            // update inputs
            from = s.from +1;
            to = s.to;
        }



    }


}

class Sol {
    int from;
    int to;

    public Sol(int a, int b) {
        this.from = a;
        this.to = b;
    }
}


class SolOrCursor {
    Sol sol;
    int from;
    int to;

    public SolOrCursor(Sol sol, int a, int b) {
        this.sol = sol;
        this.from = a;
        this.to = b;
    }

    @Override
    public String toString() {
        return "SolOrCursor{" +
                "sol=" + sol +
                ", from=" + from +
                ", to=" + to +
                '}';
    }

    public long part2Sol(List<Long> ls){
        List<Long> l  = ls.subList(this.from,this.to);
        return Collections.max(l) + Collections.min(l);
    }
}

