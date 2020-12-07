package day6;

import java.io.IOException;
import java.util.ArrayList;

public class Solution {


    public static int responseCnt(boolean[] a){
        int i= 0;
        for (boolean b : a) {
            if(b){
                i++;
            }
        }
        return i;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "data/day6.txt";

        //day3.Input input = ;
        ArrayList<boolean[]> input = new day6.Input().readFile(fileName);
        int sol2 = new day6.Input().readFilePart2(fileName);


        int max = -Integer.MAX_VALUE;
        boolean[] seats = new boolean[989 + 1];

        int sol1 =0;
        for (boolean[] group : input) {
            sol1 += responseCnt(group);
        }

        System.out.printf("sol1=%s%n",sol1);
        System.out.printf("sol1=%s",sol2);
        //String.format("u1=%s;u2=%s;u3=%s;u4=%s;", u1, u2, u3, u4);
    }

}
