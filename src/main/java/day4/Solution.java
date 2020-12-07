package day4;

import java.io.IOException;
import java.util.ArrayList;

public class Solution {


    public static void main(String[] args) throws IOException {
        String fileName = "data/day4.txt";

        //day3.Input input = ;
        ArrayList<NorthPoleCredentials> input = new Input().readFile(fileName);

        int i = 0;

        for (NorthPoleCredentials northPoleCredentials : input) {
            if (northPoleCredentials.isValid()) {
                i++;
            }
        }

        System.out.println(input.size());
        System.out.println(i);
    }
}
