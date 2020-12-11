package day10;

import com.sun.source.doctree.SystemPropertyTree;
import day9.Input;

import java.io.IOException;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        String fileName = "data/day10.txt";

        ArrayList<Long> values = new Input().readFile(fileName);
        values.add(Collections.max(values) + 3);
        values.add(0L);
        solvePart1(values);
        solvePart2(values);
    }


    public static void solvePart1(ArrayList<Long> values) {

        Collections.sort(values);

        ArrayList<Long> s = new ArrayList<>();

        Long prev = 0L;
        HashMap<Long, Integer> counter = new HashMap<>();
        for (int i = 0; i < values.size(); i++) {
            Long curr = values.get(i);
            Long k = curr - prev;
            s.add(k);
            Integer cnt = counter.getOrDefault(k, 0);
            counter.put(k, cnt + 1);
            prev = curr;
        }

        System.out.println(counter.get(3L) * counter.get(1L));
    }


    public static void solvePart2(ArrayList<Long> values) {
        long[] s = generatePath(values);
        System.out.println(s[s.length - 1]);

    }

    public static long[] generatePath(ArrayList<Long> values) {
        Collections.sort(values, Collections.reverseOrder());
        long[] s = new long[values.size()];
        System.out.println(values);
        s[0] = 1L;
        for (int i = 1; i < values.size(); i++) {
            long curr = values.get(i);
            int j = i - 1;
            long counter = 0;
            //System.out.printf("STARTING: i=%d,value=%d\n",i,curr);
            while (j >= 0 && (values.get(j) <= curr + 3)) {
                //System.out.printf("j=%d,i=%d,counter=%d,value=%d\n",j,i,counter,values.get(j));
                counter += s[j];
                j--;
            }
            s[i] = counter;
        }
        //flip(s);
        return s;
    }

    public static void flip(long[] s) {
        // flip
        for (int i = 0; i < s.length / 2; i++) {
            int j = s.length - 1 - i;
            long tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
    }
}

