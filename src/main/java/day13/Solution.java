package day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Solution {

    public static void main(String[] args) throws Exception {
        long ts = 939;
        String schedule = "7,13,x,x,59,x,31,19";

        ts = 1003240;
        schedule = "19,x,x,x,x,x,x,x,x,41,x,x,x,37,x,x,x,x,x,787,x,x,x,x,x,x,x,x,x,x,x,x,13,x,x,x,x,x,x,x,x,x,23,x,x,x,x,x,29,x,571,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17";

        ArrayList<Integer> s1 = parseSchedule(schedule);

        System.out.println(s1.toString());

        solvePart1(ts, s1);
        solvePart2(parseSchedule2(schedule));

    }

    public static ArrayList<Integer> parseSchedule(String s) {

        String[] schedule = s.split(",");
        ArrayList<Integer> s1 = new ArrayList<Integer>();
        for (String busID : schedule) {
            try {
                s1.add(Integer.parseInt(busID));
            } catch (Exception e) {
            }
        }
        return s1;
    }

    public static HashMap<Integer, Long> parseSchedule2(String s) {
        HashMap<Integer, Long> acc = new HashMap<>();
        String[] schedule = s.split(",");
        for (int i = 0; i < schedule.length; i++) {
            if (!schedule[i].equals("x")) {
                acc.put(i, Long.parseLong(schedule[i]));
            }
        }
        return acc;

    }

    public static void solvePart1(long ts, ArrayList<Integer> schedule) {

        boolean found = false;
        long inc = 1;

        while (!found) {
            //System.out.printf("trying %d \n",inc+ts);
            for (Integer l : schedule) {
                if ((inc + ts) % l == 0) {
                    System.out.printf("SOL %d %d busID=%d, Sol=%d\n", inc + ts, inc, l, inc * l);
                    found = true;
                }
            }
            inc++;
        }
    }

    public static boolean validate(long candidate, HashMap<Integer, Long> rules, long offset) {
        long i0 = candidate - offset;
        for (HashMap.Entry<Integer, Long> entry : rules.entrySet()) {
            if ( (i0 + (long) entry.getKey()) % entry.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    public static void solvePart2(HashMap<Integer, Long> schedule) {
        // We notice that at index = 19, a lot of conditions need to be satisfied:
        // we have candidate + 19 % 19 = 0
        //     ... candidate + 0  % 787 = 0
        //     ... candidate + -13  % 13 = 0
        // And they are all prime ! Nice! (no need to bother with factorization)
        // So instead, of finding candidate, we will find candidate + 19 first

        System.out.println(schedule.toString());

        long to =(long) pow(10, 8);
        for (long i19 = 0L; i19 < to; i19++) {
            long candidate = i19 * 787L * 13L * 23L * 29L;
            if (validate(candidate, schedule, 19)) {
                System.out.printf("SOL = %d", candidate - 19);
                break;
            }
        }

    }


}
