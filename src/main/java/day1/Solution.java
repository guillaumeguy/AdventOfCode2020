package day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Solution {

    public static void main(String[] args) throws IOException {
        String fileName = "data/day1.txt";

        Input input = new Input();
        ArrayList<Integer> al = input.readFile(fileName);

        HashSet<Integer> visited = new HashSet<>(al.size());
        for (Integer e : al) {
            if (visited.contains(e)) {
                System.out.println(e * (2020 - e)); // 381699
                break;
            }
            visited.add(2020 - e);
        }
    }
}



