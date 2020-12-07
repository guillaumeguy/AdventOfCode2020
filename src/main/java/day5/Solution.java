package day5;

import day4.NorthPoleCredentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Solution {

    public static int StringToInt(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'B' || s.charAt(i) == 'R') {
                sb.append(1);
            } else {
                sb.append(0);
            }

        }
        int bin = Integer.parseInt(sb.toString(), 2);
        return bin;
    }

    public static int row(String s) {
        return StringToInt(s.substring(0, 7));
    }

    public static int col(String s) {
        return StringToInt(s.substring(7));
    }

    public static void main(String[] args) throws IOException {
        String fileName = "data/day5.txt";

        //day3.Input input = ;
        ArrayList<String> input = new day5.Input().readFile(fileName);

        int max = -Integer.MAX_VALUE;
        boolean[] seats = new boolean[989 + 1];

        for (String boardingPass : input) {
            int seatID = row(boardingPass) * 8 + col(boardingPass);
            seats[seatID] = true;
            if (seatID > max) {
                max = seatID;
            }
        }

        for (int i = 0; i < seats.length; i++) {
            if (!seats[i]) {
                System.out.println(i);
            }
        }

        System.out.println(max);
    }

    //System.out.println(input.size());
    //System.out.println(i);
}
