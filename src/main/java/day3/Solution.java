package day3;

import java.io.IOException;
import java.util.ArrayList;

public class Solution {


    public static int trees(ArrayList<boolean[]> input, Slope slope) {
        int mod = input.get(0).length;
        int j = 0;
        int l = input.size() / slope.down;
        for (int i = 0; i < l; i++) {
            int stepDown = i * slope.down;
            int stepRight = i * slope.right % mod;
            if (input.get(stepDown)[stepRight]) {
                j++;
            }
        }
        return j;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "data/day3.txt";

        //day3.Input input = ;
        ArrayList<boolean[]> input = new day3.Input().readFile(fileName);

        Slope[] slopes = new Slope[]{
                new Slope(3, 1),
                new Slope(1, 1),
                new Slope(5, 1),
                new Slope(7, 1),
                new Slope(1, 2)
        };

        long acc = 1;
        for (Slope slope : slopes) {
            long e = trees(input, slope);
            System.out.println(e);
            acc *= e;
        }
        System.out.println(acc);
    }
}
