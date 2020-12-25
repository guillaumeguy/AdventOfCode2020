package day14;


import java.io.IOException;
import java.util.*;

import static java.lang.StrictMath.pow;


public class Solution {

    public static void main(String[] args) throws IOException {
        String fileName = "data/day14.txt";

        List<Instructions> instructions = new Input().readFile(fileName);

        System.out.println(instructions.size());

        solvePart1(instructions);
        //solvePart2(values);
    }


    public static void solvePart1(List<Instructions> instructions) {

        HashMap<Integer,char[]> results = new HashMap<>();
        for (Instructions i : instructions) {
            solvePart1Helper(i,results);
        }

        long t = 0;
        for (Map.Entry<Integer, char[]> e : results.entrySet()) {
            t +=  convertToInt(e.getValue());
        }

        System.out.printf("Sol=%d",t);
    }

    public static void solvePart1Helper(Instructions v,HashMap<Integer,char[]> results) {
        HashMap<Integer, Integer> masks = new HashMap<>();

        for (int i = 0; i < v.mask.length(); i++) {
            if (v.mask.charAt(i) != 'X') {
                masks.put(
                        i, (int) v.mask.charAt(i) - '0'
                );
            }
        }

        for (int i = 0; i < v.addresses.size(); i++) {
            String result = Integer.toBinaryString(v.values.get(i));
            // 36-bit Integer
            String resultWithPadding = String.format("%36s", result).replaceAll(" ", "0");

            char[] ch = new char[v.mask.length()];

            // Copy character by character into array
            for (int j = 0; j < ch.length; j++) {
                ch[j] = resultWithPadding.charAt(j) ;
                if (masks.containsKey(j)) {
                    ch[j] = masks.get(j).equals(1) ? '1' : '0' ;
                }
            }
            results.put(v.addresses.get(i),ch);
        }
    }

    public static long convertToInt(char[] binaryRepresentation){
        long t = 0L;
        int s = binaryRepresentation.length;
        for (int i = 0; i < binaryRepresentation.length; i++) {
            if(binaryRepresentation[i] - '0'> 0){
                t += (long) pow(2,s-i-1);
            }
        }
        return t;
    }

    public static void solvePart2(ArrayList<int[]> v) {
    }
}


