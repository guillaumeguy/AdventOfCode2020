package day11;

import day11.Input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Solution {

    public static void main(String[] args) throws IOException {
        String fileName = "data/day11.txt";

        ArrayList<int[]> values = new Input().readFile(fileName);

        solvePart1(values);
        solvePart2(values);
    }


    public static void solvePart1(ArrayList<int[]> v) {
        SeatMap sm = new SeatMap(v);
        System.out.println(sm.toString());
        sm.iterateState();

        for (int i = 0; i < 100; i++) {
            sm.iterateState();
            if (sm.changes == 0) {
                break;
            }
        }
        System.out.printf("occupied = %d",sm.occupiedCnt());
    }

    public static void solvePart2(ArrayList<int[]> v) {
        SeatMap sm = new SeatMap(v);
        sm.iterateState();
        sm.iterateState();

        for (int i = 0; i < 100_000; i++) {
            sm.iterateState();
            if (sm.changes == 0) {
                break;
            }
        }
        System.out.printf("occupied = %d",sm.occupiedCnt());
    }
}
