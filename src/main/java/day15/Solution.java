package day15;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.StrictMath.pow;


public class Solution {

    public static void main(String[] args) throws IOException {
        String fileName = "data/day15.txt";

        List<Integer> input = new Input().readFile(fileName);

        System.out.println(input);
        solvePart1(input,30_000_000);
        //solvePart2(values);
    }


    public static void solvePart1(List<Integer> input,int finish) {

        HashMap<Long,NumberStat> mem = new HashMap<>();
        List<Long> solution = new ArrayList<>();

        long j = 0;
        System.out.println(input.subList(0,input.size()-1));
        for (Integer i : input) {
            solution.add(i.longValue());
            if(j != input.size() -1){
                mem.put(i.longValue(),new NumberStat(j));
            }
            j++;
        }

        Long current = input.get(input.size()-1).longValue();


        for (long i = j; i < finish; i++) {
            //System.out.printf("current %d\n",current);
            if(mem.containsKey(current)){
                NumberStat sol = mem.get(current);
                solution.add(i-sol.last-1);
                sol.last = i-1;
               // System.out.printf("i = %d - last = %d (update) adding=%d to sol\n",i,current,i-sol.last);
            }else {
                solution.add(0L);
                mem.put(current,new NumberStat(i-1));
                //System.out.printf("i = %d - (new) %d\n",i,current);
            }
            current = solution.get(solution.size()-1);
        }

        //System.out.println(solution.toString());
        System.out.println(solution.get(finish-1));

    }

}


